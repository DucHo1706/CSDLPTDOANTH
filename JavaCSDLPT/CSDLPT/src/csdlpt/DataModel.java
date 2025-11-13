package csdlpt;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;
import org.json.*;

/**
 * DataModel - Lớp trung tâm xử lý kết nối, CRUD và đồng bộ dữ liệu giữa các site.
 * Giữ nguyên chức năng gốc, chỉ được viết lại cho dễ đọc, dễ bảo trì.
 */
public class DataModel {

    // ===== ĐỊNH NGHĨA CỘT THEO ENDPOINT =====
    private static final Map<String, String[]> COLUMN_MAP = Map.of(
        "/NHANVIEN/INDEX", new String[]{"Mã Nhân Viên", "Họ Tên", "Mã Chi Nhánh"},
        "/HOADON/INDEX", new String[]{"Số HD", "Tháng", "Năm", "Số HD", "Mã NV", "Số Tiền"},
        "/HOPDONG/INDEX", new String[]{"Số Hóa Đơn", "Ngày Đăng Ký", "Mã Khách Hàng", "Số Điện Kế", "KV- Định Mức", "giá"},
        "/KHACHHANG/INDEX", new String[]{"Mã Khách Hàng", "Tên Khách Hàng", "Mã Chi Nhánh"},
        "/CHINHANH/INDEX", new String[]{"Mã Chi Nhánh", "Tên chi nhánh", "Thành Phố"}
    );

    // ===== CẤU HÌNH SITE =====
    private final Map<String, String> siteMap = new HashMap<>();
    private String masterIP;

    // ===== ĐỊNH DẠNG =====
    private final NumberFormat currencyFormatter;
    private final SimpleDateFormat dateFormatter;
    private final Pattern msDatePattern;

    // ===== LUỒNG SONG SONG =====
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    // ===== KHỞI TẠO =====
    public DataModel() {
        siteMap.put("26.233.138.208", "MÁY CHÍNH");
        siteMap.put("26.52.142.67", "THÀNH PHỐ 2");
        siteMap.put("26.45.153.150", "THÀNH PHỐ 1");

        masterIP = siteMap.entrySet()
                          .stream()
                          .filter(e -> e.getValue().equals("MÁY CHÍNH"))
                          .map(Map.Entry::getKey)
                          .findFirst()
                          .orElse(null);

        if (masterIP == null) {
            System.err.println("❌ Không tìm thấy IP 'MÁY CHÍNH' trong siteMap!");
        }

        Locale localeVN = new Locale("vi", "VN");
        currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        msDatePattern = Pattern.compile("/Date\\((\\d+)\\)/");
    }

    // ===== LỚP PHỤ TRỢ =====
    private static class SiteDataResult {
        final String siteName;
        final ArrayList<ArrayList<String>> data;
        final Exception error;

        SiteDataResult(String siteName, ArrayList<ArrayList<String>> data, Exception error) {
            this.siteName = siteName;
            this.data = data;
            this.error = error;
        }
    }

    // ==============================================================
    // =============== 1️⃣ LẤY DỮ LIỆU (GET REQUEST) ================
    // ==============================================================

    public ArrayList<ArrayList<String>> get(String url, String[] keys)
            throws IOException, InterruptedException, JSONException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(java.time.Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body().trim();

        ArrayList<ArrayList<String>> dataList = new ArrayList<>();
        if (!body.startsWith("[")) {
            System.err.println("⚠️ Dữ liệu trả về từ " + url + " không phải JSONArray!");
            return dataList;
        }

        JSONArray jsonArray = new JSONArray(body);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONArray rowJson = jsonArray.getJSONArray(i);
                ArrayList<String> row = new ArrayList<>();
                for (int j = 0; j < Math.min(rowJson.length(), keys.length); j++) {
                    Object val = rowJson.opt(j);
                    row.add(val == null || val == JSONObject.NULL ? "" : val.toString().trim());
                }
                dataList.add(row);
            } catch (JSONException e) {
                System.err.println("Lỗi phân tích JSON hàng " + i + ": " + e.getMessage());
            }
        }
        return dataList;
    }

    // ==============================================================
    // =============== 2️⃣ ĐỔ DỮ LIỆU VÀO BẢNG (TABLE) ==============
    // ==============================================================

    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<ArrayList<String>> data, String[] columns) {
        if (model == null)
            model = new DefaultTableModel(columns, 0);
        else
            model.setRowCount(0);

        for (ArrayList<String> row : data) {
            Object[] rowData = row.toArray(new Object[0]);
            model.addRow(rowData);
        }
        return model;
    }

    // ==============================================================
    // =============== 3️⃣ LẤY DỮ LIỆU TỪ NHIỀU SITE SONG SONG ======
    // ==============================================================

    public void fetchAllSitesData(File fileIP, JTable table, JTextArea logArea, String endpoint) {
        String endpointKey = endpoint.toUpperCase();
        String[] columns = COLUMN_MAP.get(endpointKey);

        if (columns == null) {
            logArea.append("\n Không tìm thấy cấu hình cột cho endpoint: " + endpoint);
            return;
        }

        // Đọc file IP
        List<String> siteIPs = readIPList(fileIP, logArea);
        if (siteIPs.isEmpty()) {
            logArea.append("\n️ File IP trống hoặc lỗi đọc.");
            return;
        }

        // Thêm cột "SITE"
        String[] columnsWithSite = Arrays.copyOf(columns, columns.length + 1);
        columnsWithSite[columns.length] = "SITE";

        DefaultTableModel model = new DefaultTableModel(columnsWithSite, 0);
        table.setModel(model);

        int totalRecords = 0;
        int successSites = 0;

        // Tạo danh sách tác vụ song song
        List<Callable<SiteDataResult>> tasks = new ArrayList<>();
        for (String ip : siteIPs) {
            final String siteName = siteMap.getOrDefault(ip, "UNKNOWN");
            final String url = "http://" + ip + "/" + endpoint;

            tasks.add(() -> {
                try {
                    ArrayList<ArrayList<String>> data = get(url, columns);
                    return new SiteDataResult(siteName, data, null);
                } catch (Exception e) {
                    return new SiteDataResult(siteName, null, e);
                }
            });
        }

        // Thực thi song song
        try {
            List<Future<SiteDataResult>> futures = executor.invokeAll(tasks);
            for (Future<SiteDataResult> future : futures) {
                SiteDataResult result = future.get();

                if (result.error != null) {
                    logArea.append("\n " + result.siteName + " - Lỗi kết nối: " + result.error.getMessage());
                    continue;
                }
                if (result.data.isEmpty()) {
                    logArea.append("\n️ " + result.siteName + " - Không có dữ liệu.");
                    continue;
                }

                // Thêm dữ liệu vào bảng
                for (ArrayList<String> row : result.data) {
                    Object[] rowWithSite = Arrays.copyOf(row.toArray(), columnsWithSite.length);
                    rowWithSite[columnsWithSite.length - 1] = result.siteName;
                    model.addRow(rowWithSite);
                }

                totalRecords += result.data.size();
                successSites++;
                logArea.append("\n✅ " + result.siteName + " - Đọc " + result.data.size() + " bản ghi");
            }

        } catch (Exception e) {
            logArea.append("\n Lỗi song song: " + e.getMessage());
        }

        logArea.append("\n=== KẾT QUẢ: " + totalRecords + " bản ghi từ " + successSites + "/" + siteIPs.size() + " site ===");
    }

    // ==============================================================
    // =============== 4️⃣ POST DỮ LIỆU LÊN MÁY CHÍNH ===============
    // ==============================================================

    public boolean postToMaster(JTextArea logArea, Map<String, String> params, String endpoint) {
        if (masterIP == null) {
            logArea.append("\n Không tìm thấy IP máy chính!");
            return false;
        }

        String url = "http://" + masterIP + "/" + endpoint;
        try {
            String formData = buildFormData(params);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .timeout(java.time.Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return handlePostResponse(response.body(), logArea, endpoint);

        } catch (ConnectException e) {
            logArea.append("\n Không kết nối được tới máy chính (" + masterIP + ")");
        } catch (java.net.http.HttpTimeoutException e) {
            logArea.append("\n️ Timeout khi gửi đến máy chính (" + masterIP + ")");
        } catch (Exception e) {
            logArea.append("\n️ Lỗi POST: " + e.getMessage());
        }
        return false;
    }

    private boolean handlePostResponse(String body, JTextArea logArea, String endpoint) {
        try {
            JSONObject json = new JSONObject(body);
            boolean success = json.optBoolean("success", false);
            String message = json.optString("message", body);
            logArea.append("\n" + (success ? "" : "") + " POST " + endpoint + ": " + message);
            return success;
        } catch (JSONException e) {
            logArea.append("\n️ Phản hồi không phải JSON: " + body);
            return false;
        }
    }

    // ==============================================================
    // =============== 5️⃣ HÀM TIỆN ÍCH HỖ TRỢ ======================
    // ==============================================================

    private List<String> readIPList(File f, JTextArea logArea) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (!line.trim().isEmpty()) list.add(line.trim());
            }
        } catch (Exception e) {
            logArea.append("\n⚠️ Lỗi đọc file IP: " + e.getMessage());
        }
        return list;
    }

    private String buildFormData(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((key, value) -> {
            if (sb.length() > 0) sb.append("&");
            sb.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
              .append("=")
              .append(URLEncoder.encode(value, StandardCharsets.UTF_8));
        });
        return sb.toString();
    }

    private String formatMsDate(String msDate) {
        if (msDate == null || msDate.isEmpty()) return "";
        Matcher matcher = msDatePattern.matcher(msDate);
        if (matcher.find()) {
            try {
                long timestamp = Long.parseLong(matcher.group(1));
                return dateFormatter.format(new Date(timestamp));
            } catch (Exception ignored) {}
        }
        return msDate;
    }

    private String formatCurrency(String numStr) {
        if (numStr == null || numStr.isEmpty()) return "";
        try {
            double val = Double.parseDouble(numStr);
            return currencyFormatter.format(val);
        } catch (NumberFormatException e) {
            return numStr;
        }
    }

    // ==============================================================
    // =============== 6️⃣ COMBOBOX HỖ TRỢ ==========================
    // ==============================================================

    public void loadComboBoxData(JComboBox<String> combo, JTextArea logArea, String endpoint) {
        if (masterIP == null) {
            logArea.append("\n Không có máy chính để tải ComboBox!");
            return;
        }

        String url = "http://" + masterIP + "/" + endpoint;
        String[] keys = COLUMN_MAP.get(endpoint.toUpperCase());

        if (keys == null) {
            logArea.append("\n Không tìm thấy cột cho endpoint " + endpoint);
            return;
        }

        new Thread(() -> {
            try {
                ArrayList<ArrayList<String>> data = get(url, keys);
                SwingUtilities.invokeLater(() -> {
                    combo.removeAllItems();
                    if (data.isEmpty()) combo.addItem(" (Không có dữ liệu) ");
                    else data.forEach(row -> combo.addItem(row.get(0)));
                    logArea.append("\n Tải ComboBox " + endpoint + " thành công (" + data.size() + " mục)");
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    combo.removeAllItems();
                    combo.addItem(" (Lỗi tải dữ liệu) ");
                    logArea.append("\n Lỗi tải ComboBox: " + e.getMessage());
                });
            }
        }).start();
    }
}
