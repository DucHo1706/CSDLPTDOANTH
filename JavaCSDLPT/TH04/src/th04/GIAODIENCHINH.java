package th04;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GIAODIENCHINH extends JFrame {
    private JTable tblData;
    private JTextArea txtLog;
    private JComboBox<String> cboTable;
    private JButton btnLoad, btnAdd, btnEdit, btnDelete, btnClear;
    private JTextField txtSearch;
    private JButton btnSearch;
    
    private DataModel dataModel;
    private File ipFile;
    
    // Map lưu trữ các trường nhập liệu cho từng bảng
    private Map<String, JTextField[]> inputFieldsMap;
    private JPanel inputPanel;

    public GIAODIENCHINH() {
        initComponents();
        setupEventHandlers();
        setupInputFields();
        loadIPFile();
    }

    private void initComponents() {
        setTitle("HỆ THỐNG QUẢN LÝ ĐIỆN LỰC - PHÂN MẢNH NGANG (3 Site)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Main container với BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== HEADER =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        
        JLabel lblTitle = new JLabel("QUẢN LÝ ĐIỆN LỰC - PHÂN MẢNH NGANG (3 SITE)", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        headerPanel.add(lblTitle, BorderLayout.CENTER);
        
        // IP File info với 3 site
        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ipPanel.setOpaque(false);
        JLabel lblIPFile = new JLabel("<html>Site: <b>26.233.138.208</b> (Máy chính) | " +
                                     "<b>26.52.142.67</b> (TP2) | " +
                                     "<b>TP1 IP</b></html>");
        lblIPFile.setForeground(Color.YELLOW);
        lblIPFile.setFont(new Font("Arial", Font.PLAIN, 11));
        ipPanel.add(lblIPFile);
        headerPanel.add(ipPanel, BorderLayout.EAST);

        // ===== CONTROL PANEL =====
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Điều khiển"));
        
        cboTable = new JComboBox<>(new String[]{
            "CHINHANH", "NHANVIEN", "KHACHHANG", "HOPDONG", "HOADON"
        });
        cboTable.setPreferredSize(new Dimension(150, 30));
        
        btnLoad = createStyledButton("Tải dữ liệu", new Color(52, 152, 219));
        btnAdd = createStyledButton("Thêm", new Color(46, 204, 113));
        btnEdit = createStyledButton("Sửa", new Color(241, 196, 15));
        btnDelete = createStyledButton("Xóa", new Color(231, 76, 60));
        btnClear = createStyledButton("Làm mới", new Color(149, 165, 166));
        
        // Site selection
        JComboBox<String> cboSite = new JComboBox<>(new String[]{
            "TẤT CẢ SITE", "MÁY CHÍNH (26.233.138.208)", "THÀNH PHỐ 2 (26.52.142.67)", "THÀNH PHỐ 1"
        });
        cboSite.setPreferredSize(new Dimension(200, 30));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        txtSearch = new JTextField(15);
        txtSearch.setPreferredSize(new Dimension(150, 30));
        btnSearch = createStyledButton("Tìm kiếm", new Color(155, 89, 182));
        
        searchPanel.add(new JLabel("Site:"));
        searchPanel.add(cboSite);
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        controlPanel.add(new JLabel("Bảng dữ liệu:"));
        controlPanel.add(cboTable);
        controlPanel.add(btnLoad);
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDelete);
        controlPanel.add(btnClear);
        controlPanel.add(searchPanel);

        // ===== INPUT PANEL =====
        inputPanel = new JPanel(new GridLayout(0, 4, 10, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Nhập liệu"));
        inputPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // ===== TABLE PANEL =====
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Dữ liệu từ các Site"));
        
        tblData = new JTable();
        tblData.setAutoCreateRowSorter(true);
        tblData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblData.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblData.setFont(new Font("Arial", Font.PLAIN, 12));
        tblData.setRowHeight(25);
        
        JScrollPane tableScroll = new JScrollPane(tblData);
        tableScroll.setPreferredSize(new Dimension(getWidth(), 300));
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        // ===== LOG PANEL =====
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("Nhật ký hoạt động - Kết nối 3 Site"));
        
        txtLog = new JTextArea(8, 20);
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 11));
        txtLog.setBackground(new Color(253, 246, 227));
        
        JScrollPane logScroll = new JScrollPane(txtLog);
        logPanel.add(logScroll, BorderLayout.CENTER);
        
        // Clear log button
        JButton btnClearLog = createStyledButton("Xóa log", new Color(52, 73, 94));
        btnClearLog.addActionListener(e -> txtLog.setText(""));
        logPanel.add(btnClearLog, BorderLayout.EAST);

        // ===== LAYOUT ASSEMBLY =====
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(logPanel, BorderLayout.SOUTH);

        add(mainPanel);
        
        // Initialize data model
        dataModel = new DataModel();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private void setupEventHandlers() {
        btnLoad.addActionListener(e -> loadData());
        btnAdd.addActionListener(e -> addRecord());
        btnEdit.addActionListener(e -> editRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnClear.addActionListener(e -> clearInputs());
        btnSearch.addActionListener(e -> searchData());
        
        // Double click to edit
        tblData.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    fillInputsFromSelectedRow();
                }
            }
        });
        
        // Table change event
        cboTable.addActionListener(e -> updateInputFields());
    }

    private void setupInputFields() {
        inputFieldsMap = new HashMap<>();
        
        // Define input fields for each table
        inputFieldsMap.put("CHINHANH", new JTextField[]{
            new JTextField(), new JTextField(), new JTextField()
        });
        
        inputFieldsMap.put("NHANVIEN", new JTextField[]{
            new JTextField(), new JTextField(), new JTextField()
        });
        
        inputFieldsMap.put("KHACHHANG", new JTextField[]{
            new JTextField(), new JTextField(), new JTextField()
        });
        
        inputFieldsMap.put("HOPDONG", new JTextField[]{
            new JTextField(), new JTextField(), new JTextField(), 
            new JTextField(), new JTextField(), new JTextField()
        });
        
        inputFieldsMap.put("HOADON", new JTextField[]{
            new JTextField(), new JTextField(), new JTextField(),
            new JTextField(), new JTextField(), new JTextField()
        });
        
        updateInputFields();
    }

    private void updateInputFields() {
        String selectedTable = (String) cboTable.getSelectedItem();
        JTextField[] fields = inputFieldsMap.get(selectedTable);
        String[] labels = getFieldLabels(selectedTable);
        
        inputPanel.removeAll();
        
        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(new JLabel(labels[i] + ":"));
            inputPanel.add(fields[i]);
        }
        
        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private String[] getFieldLabels(String table) {
        switch (table) {
            case "CHINHANH":
                return new String[]{"Mã CN", "Tên CN", "Thành phố"};
            case "NHANVIEN":
                return new String[]{"Mã NV", "Họ tên", "Mã CN"};
            case "KHACHHANG":
                return new String[]{"Mã KH", "Tên KH", "Mã CN"};
            case "HOPDONG":
                return new String[]{"Số HD", "Ngày ký", "Mã KH", "Số điện kế", "KW định mức", "Đơn giá KW"};
            case "HOADON":
                return new String[]{"Số HDN", "Tháng", "Năm", "Số HD", "Mã NV", "Số tiền"};
            default:
                return new String[]{};
        }
    }

    private void loadIPFile() {
        ipFile = new File("ips.txt");
        if (!ipFile.exists()) {
            // Tạo file IP mới với 3 site của bạn
            createIPFile();
        } else {
            log("Đã tải file IP: " + ipFile.getAbsolutePath());
        }
    }

    private void createIPFile() {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("ips.txt");
            writer.write("26.233.138.208\n"); // Máy chính
            writer.write("26.52.142.67\n");   // Thành phố 2
            writer.write("26.45.153.150\n"); // Thành phố 1 (tạm thời dùng cùng IP máy chính)
            writer.close();
            ipFile = new File("ips.txt");
            log("Đã tạo file IP mới với 3 site");
        } catch (Exception e) {
            showError("Lỗi tạo file IP: " + e.getMessage());
        }
    }

    private void loadData() {
        String table = (String) cboTable.getSelectedItem();
        String endpoint = table.toLowerCase() + "/index";
        
        log("=== ĐANG TẢI DỮ LIỆU TỪ BẢNG: " + table + " ===");
        dataModel.getDataFromIPFile(ipFile, tblData, txtLog, endpoint);
    }

    private void addRecord() {
        String table = (String) cboTable.getSelectedItem();
        Map<String, String> params = getInputParams();
        
        if (params == null) return;
        
        String endpoint = table.toLowerCase() + "/add";
        log("Đang thêm dữ liệu vào bảng: " + table);
        dataModel.postToFile(ipFile, txtLog, params, endpoint);
        
        // Reload data after adding
        SwingUtilities.invokeLater(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            loadData();
        });
    }

    private void editRecord() {
        int selectedRow = tblData.getSelectedRow();
        if (selectedRow == -1) {
            showError("Vui lòng chọn một bản ghi để sửa!");
            return;
        }
        
        String table = (String) cboTable.getSelectedItem();
        Map<String, String> params = getInputParams();
        
        if (params == null) return;
        
        String endpoint = table.toLowerCase() + "/update";
        log("Đang cập nhật dữ liệu bảng: " + table);
        dataModel.postToFile(ipFile, txtLog, params, endpoint);
        
        // Reload data after editing
        SwingUtilities.invokeLater(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            loadData();
        });
    }

    private void deleteRecord() {
        int selectedRow = tblData.getSelectedRow();
        if (selectedRow == -1) {
            showError("Vui lòng chọn một bản ghi để xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn xóa bản ghi này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) return;
        
        String table = (String) cboTable.getSelectedItem();
        String primaryKey = getPrimaryKeyValue(selectedRow);
        
        if (primaryKey == null) return;
        
        Map<String, String> params = new HashMap<>();
        params.put(getPrimaryKeyField(table), primaryKey);
        
        String endpoint = table.toLowerCase() + "/delete";
        log("Đang xóa dữ liệu từ bảng: " + table + " - Mã: " + primaryKey);
        dataModel.postToFile(ipFile, txtLog, params, endpoint);
        
        // Reload data after deleting
        SwingUtilities.invokeLater(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            loadData();
        });
    }

    private void searchData() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            loadData();
            return;
        }
        
        // Simple search - filter table rows
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblData.getModel());
        tblData.setRowSorter(sorter);
        
        RowFilter<TableModel, Object> filter = RowFilter.regexFilter("(?i)" + keyword);
        sorter.setRowFilter(filter);
        log("Đã tìm kiếm: '" + keyword + "' - Tìm thấy: " + tblData.getRowCount() + " kết quả");
    }

    private Map<String, String> getInputParams() {
        String table = (String) cboTable.getSelectedItem();
        JTextField[] fields = inputFieldsMap.get(table);
        String[] fieldNames = getParamFieldNames(table);
        
        Map<String, String> params = new HashMap<>();
        
        for (int i = 0; i < fields.length; i++) {
            String value = fields[i].getText().trim();
            if (value.isEmpty() && i == 0) { // Primary key should not be empty for add
                showError(fieldNames[i] + " không được để trống!");
                return null;
            }
            params.put(fieldNames[i], value);
        }
        
        return params;
    }

    private String[] getParamFieldNames(String table) {
        switch (table) {
            case "CHINHANH":
                return new String[]{"maCN", "tenCN", "thanhpho"};
            case "NHANVIEN":
                return new String[]{"maNV", "hoten", "maCN"};
            case "KHACHHANG":
                return new String[]{"maKH", "tenKH", "maCN"};
            case "HOPDONG":
                return new String[]{"soHD", "ngayKy", "maKH", "soDienKe", "kwDinhMuc", "dongiaKW"};
            case "HOADON":
                return new String[]{"soHDN", "thang", "nam", "soHD", "maNV", "soTien"};
            default:
                return new String[]{};
        }
    }

    private String getPrimaryKeyField(String table) {
        switch (table) {
            case "CHINHANH": return "maCN";
            case "NHANVIEN": return "maNV";
            case "KHACHHANG": return "maKH";
            case "HOPDONG": return "soHD";
            case "HOADON": return "soHDN";
            default: return "";
        }
    }

    private String getPrimaryKeyValue(int row) {
        int modelRow = tblData.convertRowIndexToModel(row);
        String table = (String) cboTable.getSelectedItem();
        String pkField = getPrimaryKeyField(table);
        
        // Find column index for primary key
        for (int i = 0; i < tblData.getColumnCount(); i++) {
            if (tblData.getColumnName(i).equalsIgnoreCase(pkField)) {
                return tblData.getModel().getValueAt(modelRow, i).toString();
            }
        }
        
        return null;
    }

    private void fillInputsFromSelectedRow() {
        int selectedRow = tblData.getSelectedRow();
        if (selectedRow == -1) return;
        
        String table = (String) cboTable.getSelectedItem();
        JTextField[] fields = inputFieldsMap.get(table);
        int modelRow = tblData.convertRowIndexToModel(selectedRow);
        
        for (int i = 0; i < fields.length && i < tblData.getColumnCount(); i++) {
            Object value = tblData.getModel().getValueAt(modelRow, i);
            fields[i].setText(value != null ? value.toString() : "");
        }
        
        log("Đã điền form từ bản ghi được chọn");
    }

    private void clearInputs() {
        String table = (String) cboTable.getSelectedItem();
        JTextField[] fields = inputFieldsMap.get(table);
        
        for (JTextField field : fields) {
            field.setText("");
        }
        
        txtSearch.setText("");
        // Clear search filter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblData.getModel());
        tblData.setRowSorter(sorter);
        
        log("Đã làm mới form nhập liệu và bộ lọc tìm kiếm");
    }

    private void log(String message) {
        txtLog.append("\n[" + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + message);
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        log("LỖI: " + message);
    }

 public static void main(String[] args) {
    // Đơn giản chỉ cần chạy ứng dụng, không cần set look and feel
    SwingUtilities.invokeLater(() -> {
        new GIAODIENCHINH().setVisible(true);
    });
}}