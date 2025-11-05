package th04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class DataModel {

    private static final Map<String, String[]> COLUMNS_MAP = Map.of(
        "/NHANVIEN", new String[]{"maNV", "hoten", "maCN"},
        "/HOADON", new String[]{"soHDN", "thang", "nam", "soHD", "maNV", "soTien"},
        "/HOPDONG", new String[]{"soHD", "ngayKy", "maKH", "soDienKe", "kwDinhMuc", "dongiaKW"},
        "/KHACHHANG", new String[]{"maKH", "tenKH", "maCN"},
        "/CHINHANH", new String[]{"maCN", "tenCN", "thanhpho"}
    );

    // Map IP với tên site
    private Map<String, String> siteMap = new HashMap<>();

     public DataModel() {
        // Khởi tạo map site với 3 IP chính xác
        siteMap.put("26.233.138.208", "MÁY CHÍNH");
        siteMap.put("26.52.142.67", "THÀNH PHỐ 2");
        siteMap.put("26.45.153.150", "THÀNH PHỐ 1"); // IP mới
    }


    public ArrayList<ArrayList<String>> get(String url, String[] keys) 
            throws IOException, InterruptedException, JSONException {
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(java.time.Duration.ofSeconds(10))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = new String(response.body().getBytes(), StandardCharsets.UTF_8).trim();
        System.out.println(">>> Response từ " + url + ": " + body);

        ArrayList<ArrayList<String>> datalist = new ArrayList<>();

        if (body.startsWith("[")) {
            JSONArray ja = new JSONArray(body);
            for (int i = 0; i < ja.length(); i++) {
                try {
                    Object item = ja.get(i);
                    ArrayList<String> row = new ArrayList<>();

                    if (item instanceof JSONArray) {
                        JSONArray rowJsonArray = (JSONArray) item;
                        int limit = Math.min(rowJsonArray.length(), keys.length);
                        for (int j = 0; j < limit; j++) {
                            Object value = rowJsonArray.opt(j);
                            row.add(value == null || value == JSONObject.NULL ? "" : value.toString().trim());
                        }
                    } else if (item instanceof JSONObject) {
                        JSONObject rowJsonObject = (JSONObject) item;
                        for (String key : keys) {
                            Object value = rowJsonObject.opt(key);
                            row.add(value == null || value == JSONObject.NULL ? "" : value.toString().trim());
                        }
                    } else {
                        row.add(item.toString().trim());
                    }

                    while (row.size() < keys.length) {
                        row.add("");
                    }

                    datalist.add(row);

                } catch (JSONException e) {
                    System.err.println("⚠️ Lỗi parsing hàng: " + e.getMessage());
                }
            }
        } else {
            System.err.println("⚠️ Cảnh báo: Dữ liệu trả về từ " + url + " không phải JSONArray!");
        }

        return datalist;
    }

    public DefaultTableModel addTableModel(DefaultTableModel tableModel, ArrayList<ArrayList<String>> d, String tenCot[]) {
        if (tableModel == null) {
            tableModel = new DefaultTableModel(tenCot, 0);
        } else {
            tableModel.setRowCount(0);
        }
        for (int i = 0; i < d.size(); i++) {
            Object o[] = new Object[tenCot.length];
            int limit = Math.min(d.get(i).size(), tenCot.length);
            for (int j = 0; j < limit; j++) {
                o[j] = d.get(i).get(j);
            }
            tableModel.addRow(o);
        }
        return tableModel;
    }

    /**
     * PHIÊN BẢN MỚI: Load dữ liệu từ TẤT CẢ site hoạt động
     */
    public void getDataFromAllSites(File f, JTable tblResult, JTextArea txtError, String endpoint) {
        if (f == null || !f.exists()) {
            txtError.append("\nFile IP không tồn tại: " + f.getAbsolutePath());
            return;
        }

        String controllerName = endpoint.split("/")[0].toUpperCase(); 
        String mapKey = "/" + controllerName;
        String[] tenCot = COLUMNS_MAP.get(mapKey);
        String[] keys = tenCot; 

        if (tenCot == null) {
            txtError.append("\nLỗi: Không tìm thấy định nghĩa cột cho: " + controllerName);
            return;
        }

        ArrayList<String> aIP = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = bf.readLine()) != null) {
                aIP.add(line.trim());
            }
        } catch (Exception e) {
            txtError.append("\nLỗi đọc file IP: " + e.getMessage());
            return;
        }

        // Tạo model mới với cột bổ sung để hiển thị site
        String[] columnsWithSite = new String[tenCot.length + 1];
        System.arraycopy(tenCot, 0, columnsWithSite, 0, tenCot.length);
        columnsWithSite[tenCot.length] = "SITE";
        
        DefaultTableModel model = new DefaultTableModel(columnsWithSite, 0);
        tblResult.setModel(model);

        int totalRecords = 0;
        int successfulSites = 0;
        
        // Lặp qua TẤT CẢ 3 site và hợp nhất dữ liệu
        for (String ip : aIP) {
            if(ip.isEmpty()) continue;
            
            String siteName = siteMap.getOrDefault(ip, "UNKNOWN");
            String url = "http://" + ip + "/" + endpoint;

            try {
                ArrayList<ArrayList<String>> data = this.get(url, keys);
                
                if (data.isEmpty()) {
                    txtError.append("\nℹ️ " + siteName + " (" + ip + ") hoạt động nhưng không có dữ liệu.");
                    continue;
                }

                // Thêm dữ liệu từ site này vào table
                for (ArrayList<String> row : data) {
                    Object[] rowWithSite = new Object[columnsWithSite.length];
                    for (int i = 0; i < row.size() && i < tenCot.length; i++) {
                        rowWithSite[i] = row.get(i);
                    }
                    rowWithSite[tenCot.length] = siteName; // Thêm cột site
                    model.addRow(rowWithSite);
                }
                
                totalRecords += data.size();
                successfulSites++;
                txtError.append("\n✅ " + siteName + " (" + ip + "): " + data.size() + " bản ghi");

            } catch (ConnectException e1) {
                txtError.append("\n❌ " + siteName + " (" + ip + ") - Không kết nối được");
            } catch (Exception e2) {
                txtError.append("\n⚠️ Lỗi từ " + siteName + " (" + ip + "): " + e2.getMessage());
            }
        }

        txtError.append("\n=== KẾT QUẢ: " + totalRecords + " bản ghi từ " + successfulSites + "/3 site ===");
    }
     public String determineTargetSite(Map<String, String> params, String tableName) {
        if (params == null) return "MÁY CHÍNH";
        
        // Logic xác định site dựa trên mã chi nhánh (maCN)
        String maCN = null;
        
        if (tableName.equalsIgnoreCase("CHINHANH")) {
            maCN = params.get("maCN");
        } else if (tableName.equalsIgnoreCase("NHANVIEN")) {
            maCN = params.get("maCN");
        } else if (tableName.equalsIgnoreCase("KHACHHANG")) {
            maCN = params.get("maCN");
        } else if (tableName.equalsIgnoreCase("HOPDONG")) {
            // Cần lấy maCN từ maKH
            String maKH = params.get("maKH");
            if (maKH != null) {
                // Giả sử mã KH có thể ánh xạ đến chi nhánh
                // Trong thực tế, cần query để lấy maCN từ maKH
            }
        }
        
        // Ánh xạ mã chi nhánh đến site
        if (maCN != null) {
            if (maCN.equals("CN01") || maCN.equals("CN02")) {
                return "THÀNH PHỐ 1";  // Các chi nhánh thuộc TP1
            } else if (maCN.equals("CN03") || maCN.equals("CN04")) {
                return "THÀNH PHỐ 2";  // Các chi nhánh thuộc TP2
            } else if (maCN.equals("CN05")) {
                return "MÁY CHÍNH";    // Chi nhánh thuộc máy chính
            }
        }
        
        // Mặc định gửi đến máy chính
        return "MÁY CHÍNH";
    }
    /**
     * PHIÊN BẢN MỚI: POST đến site cụ thể dựa trên dữ liệu
     */
    public void postToSpecificSite(File f, JTextArea txtError, Map<String, String> params, 
                                 String endpoint, String targetSite) {
        String targetIP = getIPForSite(targetSite);
        if (targetIP == null) {
            txtError.append("\n❌ Không tìm thấy IP cho site: " + targetSite);
            return;
        }

        String url = "http://" + targetIP + "/" + endpoint;
        
        try {
            HttpClient client = HttpClient.newHttpClient();

            StringBuilder formData = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (formData.length() > 0) {
                    formData.append("&");
                }
                String encodedValue = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8); 
                formData.append(entry.getKey()).append("=").append(encodedValue);
            }

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData.toString()))
                .timeout(java.time.Duration.ofSeconds(10))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            String result = response.body();
            boolean success = response.statusCode() >= 200 && response.statusCode() < 300;
            
            if (success) {
                txtError.append("\n✅ POST đến " + targetSite + " (" + targetIP + "): THÀNH CÔNG - " + result);
            } else {
                txtError.append("\n❌ POST đến " + targetSite + " (" + targetIP + "): THẤT BẠI - " + result);
            }

        } catch (ConnectException e1) {
            txtError.append("\n❌ Không kết nối được đến " + targetSite + " (" + targetIP + ")");
        } catch (java.net.http.HttpTimeoutException e) {
            txtError.append("\n⏰ Timeout kết nối đến " + targetSite + " (" + targetIP + ")");
        } catch (Exception e) {
            txtError.append("\n⚠️ Lỗi POST đến " + targetSite + " (" + targetIP + "): " + e.getMessage());
        }
    }


    /**
     * Xác định IP dựa trên tên site
     */
   private String getIPForSite(String siteName) {
        for (Map.Entry<String, String> entry : siteMap.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(siteName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Xác định site dựa trên dữ liệu (ví dụ: mã chi nhánh)
     */
    public String determineSiteFromData(Map<String, String> params, String tableName) {
        // Logic xác định site dựa trên dữ liệu
        // Ví dụ: nếu bảng là CHINHANH, kiểm tra mã CN để xác định site
        if (tableName.equalsIgnoreCase("CHINHANH")) {
            String maCN = params.get("maCN");
            if (maCN != null) {
                if (maCN.startsWith("CN01") || maCN.startsWith("CN02")) {
                    return "THÀNH PHỐ 1";
                } else if (maCN.startsWith("CN03") || maCN.startsWith("CN04")) {
                    return "THÀNH PHỐ 2";
                }
            }
        }
        
        // Mặc định gửi đến máy chính
        return "MÁY CHÍNH";
    }

    // Giữ lại phương thức cũ để tương thích
    public void getDataFromIPFile(File f, JTable tblResult, JTextArea txtError, String endpoint) {
        getDataFromAllSites(f, tblResult, txtError, endpoint);
    }

    public void postToFile(File f, JTextArea txtError, Map<String, String> params, String endpoint) {
        // Mặc định gửi đến máy chính
        postToSpecificSite(f, txtError, params, endpoint, "MÁY CHÍNH");
    }
}