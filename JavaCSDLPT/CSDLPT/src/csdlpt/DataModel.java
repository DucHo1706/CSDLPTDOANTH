package csdlpt;

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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable; // Thêm thư viện Song Song
import java.util.concurrent.ExecutorService; // Thêm thư viện Song Song
import java.util.concurrent.Executors; // Thêm thư viện Song Song
import java.util.concurrent.Future; // Thêm thư viện Song Song
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class DataModel {

    // (Giữ nguyên) Định nghĩa cột
    private static final Map<String, String[]> COLUMNS_MAP = Map.of(
        "/NHANVIEN/INDEX", new String[]{"maNV", "hoten", "maCN"},
        "/HOADON/INDEX", new String[]{"soHDN", "thang", "nam", "soHD", "maNV", "soTien"},
        "/HOPDONG/INDEX", new String[]{"soHD", "ngayKy", "maKH", "soDienKe", "kwDinhMuc", "dongiaKW"},
        "/KHACHHANG/INDEX", new String[]{"maKH", "tenKH", "maCN"},
        "/CHINHANH/INDEX", new String[]{"maCN", "tenCN", "thanhpho"}
    );

    private Map<String, String> siteMap = new HashMap<>();
    private String masterIP = null;

    // (Giữ nguyên) Các biến định dạng
    private final NumberFormat currencyFormatter;
    private final SimpleDateFormat dateFormatter;
    private final Pattern msDatePattern;
    
    // === THÊM MỚI: BỂ BƠI LUỒNG (THREAD POOL) ===
    // Tạo một bể bơi có 3 luồng (cho 3 server)
    private final ExecutorService executor;
    // ===========================================

     public DataModel() {
         siteMap.put("26.233.138.208", "MÁY CHÍNH");
         siteMap.put("26.52.142.67", "THÀNH PHỐ 2");
         siteMap.put("26.45.153.150", "THÀNH PHỐ 1");
         
         for (Map.Entry<String, String> entry : siteMap.entrySet()) {
             if (entry.getValue().equals("MÁY CHÍNH")) {
                 masterIP = entry.getKey();
                 break;
             }
         }
         
         if (masterIP == null) {
             System.err.println("!!! CẤU HÌNH LỖI: Không tìm thấy 'MÁY CHÍNH' trong siteMap");
         }
         
         Locale localeVN = new Locale("vi", "VN");
         currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
         dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
         msDatePattern = Pattern.compile("/Date\\((\\d+)\\)/");
         
         // === THÊM MỚI: KHỞI TẠO BỂ BƠI LUỒNG ===
         executor = Executors.newFixedThreadPool(3);
         // =======================================
    }
     
    /**
     * Lớp nội (inner class) để chứa kết quả trả về từ luồng
     */
    private class SiteDataResult {
        String siteName;
        ArrayList<ArrayList<String>> data;
        Exception error;

        SiteDataResult(String siteName, ArrayList<ArrayList<String>> data, Exception error) {
            this.siteName = siteName;
            this.data = data;
            this.error = error;
        }
    }


    /**
     * Hàm GET (Không thay đổi)
     */
    public ArrayList<ArrayList<String>> get(String url, String[] keys) 
            throws IOException, InterruptedException, JSONException {
        // ... (Giữ nguyên code hàm này) ...
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(java.time.Duration.ofSeconds(10))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body().trim();
        ArrayList<ArrayList<String>> datalist = new ArrayList<>();

        if (body.startsWith("[")) {
            JSONArray ja = new JSONArray(body);
            for (int i = 0; i < ja.length(); i++) {
                try {
                    JSONArray rowJsonArray = ja.getJSONArray(i); 
                    ArrayList<String> row = new ArrayList<>();
                    int limit = Math.min(rowJsonArray.length(), keys.length);
                    for (int j = 0; j < limit; j++) {
                        Object value = rowJsonArray.opt(j);
                        row.add(value == null || value == JSONObject.NULL ? "" : value.toString().trim());
                    }
                    datalist.add(row);
                } catch (JSONException e) {
                     System.err.println("Lỗi parsing hàng: " + e.getMessage());
                }
            }
        } else {
             System.err.println("Cảnh báo: Dữ liệu trả về từ " + url + " không phải JSONArray!");
        }
        return datalist;
    }

    /**
     * Hàm đổ dữ liệu vào JTable (Không thay đổi)
     */
    public DefaultTableModel addTableModel(DefaultTableModel tableModel, ArrayList<ArrayList<String>> d, String tenCot[]) {
        // ... (Giữ nguyên code hàm này) ...
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
     * ĐỌC DỮ LIỆU TỪ TẤT CẢ SITE (ĐÃ VIẾT LẠI ĐỂ CHẠY SONG SONG)
     */
    public void getDataFromAllSites(File f, JTable tblResult, JTextArea txtError, String endpoint) {
        String endpointKey = endpoint.toUpperCase();
        String[] tenCot = COLUMNS_MAP.get(endpointKey);
        
        if (tenCot == null) {
            txtError.append("\nLỗi: Không tìm thấy định nghĩa cột cho: " + endpoint);
            return;
        }

        ArrayList<String> aIP = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if(!line.trim().isEmpty()) aIP.add(line.trim());
            }
        } catch (Exception e) {
            txtError.append("\nLỗi đọc file IP: " + e.getMessage());
            return;
        }

        // Cấu hình bảng
        String[] columnsWithSite = new String[tenCot.length + 1];
        System.arraycopy(tenCot, 0, columnsWithSite, 0, tenCot.length);
        columnsWithSite[tenCot.length] = "SITE"; 
        
        DefaultTableModel model = new DefaultTableModel(columnsWithSite, 0);
        tblResult.setModel(model);

        int totalRecords = 0;
        int successfulSites = 0;

        // === BẮT ĐẦU THAY ĐỔI: TẠO CÁC TÁC VỤ SONG SONG ===
        
        List<Callable<SiteDataResult>> tasks = new ArrayList<>();
        
        // 1. Tạo 3 tác vụ (chưa chạy)
        for (String ip : aIP) {
            final String currentIp = ip;
            final String siteName = siteMap.getOrDefault(currentIp, "UNKNOWN");
            final String url = "http://" + currentIp + "/" + endpoint;

            tasks.add(() -> { // Đây là một Callable (tác vụ có trả về kết quả)
                try {
                    // Tác vụ này sẽ chạy trên luồng riêng
                    ArrayList<ArrayList<String>> data = this.get(url, tenCot);
                    return new SiteDataResult(siteName, data, null); // Thành công
                } catch (Exception e) {
                    return new SiteDataResult(siteName, null, e); // Thất bại
                }
            });
        }

        // 2. Thực thi TẤT CẢ tác vụ CÙNG LÚC và đợi
        try {
            List<Future<SiteDataResult>> futures = executor.invokeAll(tasks);

            // 3. Xử lý kết quả (Sau khi tất cả đã xong)
            for (Future<SiteDataResult> future : futures) {
                SiteDataResult result = future.get(); // Lấy kết quả

                if (result.error != null) {
                    // Lỗi (ví dụ: ConnectException, Timeout)
                    txtError.append("\n " + result.siteName + " - Không kết nối được: " + result.error.getMessage());
                } else if (result.data.isEmpty()) {
                    txtError.append("\n " + result.siteName + " - Hoạt động nhưng không có dữ liệu.");
                } else {
                    // Thành công, đổ dữ liệu vào bảng
                    for (ArrayList<String> row : result.data) {
                        Object[] rowWithSite = new Object[columnsWithSite.length];
                        for (int i = 0; i < row.size(); i++) {
                            // Định dạng
                            String columnName = tenCot[i];
                            String value = row.get(i);
                            if (endpointKey.equals("/HOPDONG/INDEX") && columnName.equals("ngayKy")) {
                                rowWithSite[i] = formatMsDate(value);
                            } else if (endpointKey.equals("/HOADON/INDEX") && columnName.equals("soTien")) {
                                rowWithSite[i] = formatCurrency(value);
                            } else if (endpointKey.equals("/HOPDONG/INDEX") && columnName.equals("dongiaKW")) {
                                rowWithSite[i] = formatCurrency(value);
                            } else {
                                rowWithSite[i] = value;
                            }
                        }
                        rowWithSite[columnsWithSite.length - 1] = result.siteName; 
                        model.addRow(rowWithSite);
                    }
                    totalRecords += result.data.size();
                    successfulSites++;
                    txtError.append("\n ĐỌC từ " + result.siteName + ": " + result.data.size() + " bản ghi");
                }
            }
        } catch (Exception e) {
            txtError.append("\n Lỗi nghiêm trọng khi thực thi song song: " + e.getMessage());
        }
        // === KẾT THÚC THAY ĐỔI ===

        txtError.append("\n=== KẾT QUẢ ĐỌC: " + totalRecords + " bản ghi từ " + successfulSites + "/" + aIP.size() + " site ===");
    }


public boolean postToMaster(JTextArea txtError, Map<String, String> params, String endpoint) {
        if (masterIP == null) {
            txtError.append("\n LỖI NGHIÊM TRỌNG: Không thể POST vì không tìm thấy IP MÁY CHÍNH.");
            return false; // THAY ĐỔI: Trả về false
        }
        String url = "http://" + masterIP + "/" + endpoint;
        String targetSite = "MÁY CHÍNH";
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
            try {
                JSONObject jsonResponse = new JSONObject(result);
                boolean success = jsonResponse.optBoolean("success", false);
                String message = jsonResponse.optString("message", result);
                if (success) {
                    txtError.append("\n GHI " + endpoint + " đến " + targetSite + ": THÀNH CÔNG - " + message);
                    return true; // THAY ĐỔI: Trả về true
                } else {
                    txtError.append("\n GHI " + endpoint + " đến " + targetSite + ": THẤT BẠI - " + message);
                    return false; // THAY ĐỔI: Trả về false
                }
            } catch (JSONException e) {
                txtError.append("\n Phản hồi không phải JSON từ " + targetSite + ": " + result);
                return false; // THAY ĐỔI: Trả về false
            }
        } catch (ConnectException e1) {
            txtError.append("\n Không kết nối được đến " + targetSite + " (" + masterIP + ") để GHI");
        } catch (java.net.http.HttpTimeoutException e) {
            txtError.append("\n Timeout kết nối đến " + targetSite + " (" + masterIP + ")");
        } catch (Exception e) {
            txtError.append("\n Lỗi GHI đến " + targetSite + " (" + masterIP + "): " + e.getMessage());
        }
        
        return false; // THAY ĐỔI: Trả về false cho tất cả các lỗi catch
    }
    
    // === CÁC HÀM HỖ TRỢ ĐỊNH DẠNG MỚI (Không thay đổi) ===

    private String formatMsDate(String msDate) {
        // ... (Giữ nguyên code hàm này) ...
        if (msDate == null || msDate.isEmpty()) {
            return "";
        }
        Matcher matcher = msDatePattern.matcher(msDate);
        if (matcher.find()) {
            try {
                long timestamp = Long.parseLong(matcher.group(1));
                Date date = new Date(timestamp);
                return dateFormatter.format(date);
            } catch (Exception e) {
                return msDate; 
            }
        }
        return msDate; 
    }

    private String formatCurrency(String numberStr) {
        // ... (Giữ nguyên code hàm này) ...
        if (numberStr == null || numberStr.isEmpty()) {
            return "";
        }
        try {
            double value = Double.parseDouble(numberStr);
            return currencyFormatter.format(value);
        } catch (NumberFormatException e) {
            return numberStr; 
        }
    }
}