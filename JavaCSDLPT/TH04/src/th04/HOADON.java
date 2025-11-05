package th04;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class HOADON extends javax.swing.JFrame {

    private DefaultTableModel tblmodel = null;
    private boolean isEditing = false;
    private String originalSoHDN = "";

    public HOADON() {
        initComponents();
        initCustomComponents();
        loadData();
        
        // Enter key support for search
        setupEnterKeySupport();
    }

    private void initCustomComponents() {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Customize table
        customizeTable();
        
        // Set placeholder texts
        setPlaceholderTexts();
    }

    private void customizeTable() {
        tblResult.setRowHeight(25);
        tblResult.setSelectionBackground(new Color(220, 240, 255));
        tblResult.setSelectionForeground(Color.BLACK);
        tblResult.setGridColor(new Color(240, 240, 240));
        tblResult.setShowGrid(true);
        
        // Center align header
        ((DefaultTableCellRenderer) tblResult.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(JLabel.CENTER);
    }

    private void setPlaceholderTexts() {
        setPlaceholder(txtSoHDN, "Nhập số hóa đơn...");
        setPlaceholder(txtThang, "Tháng (1-12)...");
        setPlaceholder(txtNam, "Năm...");
        setPlaceholder(txtSoHD, "Nhập số hợp đồng...");
        setPlaceholder(txtMaNV, "Nhập mã nhân viên...");
        setPlaceholder(txtSoTien, "Nhập số tiền...");
    }

    private void setPlaceholder(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.setText(placeholder);
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    private void setupEnterKeySupport() {
        // Search on Enter in any field
        Action searchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        };
        
        txtSoHDN.addActionListener(searchAction);
        txtThang.addActionListener(searchAction);
        txtNam.addActionListener(searchAction);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Initialize all components
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoHDN = new javax.swing.JTextField();
        txtThang = new javax.swing.JTextField();
        txtNam = new javax.swing.JTextField();
        txtSoHD = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        txtSoTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ HÓA ĐƠN");
        setPreferredSize(new Dimension(1000, 600));

        // Configure table
        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Số HDN", "Tháng", "Năm", "Số HD", "Mã NV", "Số Tiền"
            }
        ) {
            Class[] types = new Class[]{
                String.class, String.class, String.class, String.class, String.class, Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        
        jScrollPane1.setViewportView(tblResult);
        jScrollPane1.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        // Error area
        txtError.setColumns(20);
        txtError.setRows(5);
        txtError.setBackground(new Color(255, 245, 245));
        txtError.setForeground(Color.RED);
        jScrollPane2.setViewportView(txtError);
        jScrollPane2.setBorder(BorderFactory.createTitledBorder("Thông báo"));

        // Buttons with icons
        btnAdd.setText("Thêm");
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/icons/add.png"))); // Add appropriate icon
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnDelete.setText("Xóa");
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnEdit.setText("Sửa");
        btnEdit.setIcon(new ImageIcon(getClass().getResource("/icons/edit.png")));
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnSave.setText("Lưu");
        btnSave.setIcon(new ImageIcon(getClass().getResource("/icons/save.png")));
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnSearch.setText("Tìm Kiếm");
        btnSearch.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        btnCancel.setText("Hủy");
        btnCancel.setIcon(new ImageIcon(getClass().getResource("/icons/cancel.png")));
        btnCancel.addActionListener(this::btnCancelActionPerformed);

        // Labels
        jLabel1.setText("Số Hóa Đơn *");
        jLabel2.setText("Tháng *");
        jLabel3.setText("Năm *");
        jLabel4.setText("Số Hợp Đồng *");
        jLabel5.setText("Mã Nhân Viên *");
        jLabel6.setText("Số Tiền *");

        jLabel7.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel7.setForeground(new java.awt.Color(0, 102, 204));
        jLabel7.setText("QUẢN LÝ HÓA ĐƠN");

        // Create panels for better organization
        jPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Layout organization
        organizeLayout();
    }

    private void organizeLayout() {
        // Main container with BorderLayout
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 245, 255));
        headerPanel.add(jLabel7);
        getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Center panel with form and table
        JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplitPane.setDividerLocation(400);
        centerSplitPane.setResizeWeight(0.4);

        // Form panel
        JPanel formPanel = createFormPanel();
        centerSplitPane.setLeftComponent(formPanel);

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(jScrollPane1, BorderLayout.CENTER);
        centerSplitPane.setRightComponent(tablePanel);

        getContentPane().add(centerSplitPane, BorderLayout.CENTER);

        // Bottom panel with error area
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(jScrollPane2, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        // Form fields
        JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        fieldsPanel.add(jLabel1);
        fieldsPanel.add(txtSoHDN);
        fieldsPanel.add(jLabel2);
        fieldsPanel.add(txtThang);
        fieldsPanel.add(jLabel3);
        fieldsPanel.add(txtNam);
        fieldsPanel.add(jLabel4);
        fieldsPanel.add(txtSoHD);
        fieldsPanel.add(jLabel5);
        fieldsPanel.add(txtMaNV);
        fieldsPanel.add(jLabel6);
        fieldsPanel.add(txtSoTien);

        // Buttons panel
        JPanel buttonsPanel1 = new JPanel(new FlowLayout());
        buttonsPanel1.add(btnAdd);
        buttonsPanel1.add(btnEdit);
        buttonsPanel1.add(btnDelete);

        JPanel buttonsPanel2 = new JPanel(new FlowLayout());
        buttonsPanel2.add(btnSave);
        buttonsPanel2.add(btnSearch);
        buttonsPanel2.add(btnCancel);

        formPanel.add(fieldsPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(buttonsPanel1);
        formPanel.add(buttonsPanel2);

        return formPanel;
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validateForm()) return;
        
        try {
            File f = new File("D:/IP_HoaDon.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHDN", getFieldValue(txtSoHDN));
            params.put("thang", getFieldValue(txtThang));
            params.put("nam", getFieldValue(txtNam));
            params.put("soHD", getFieldValue(txtSoHD));
            params.put("maNV", getFieldValue(txtMaNV));
            params.put("soTien", getFieldValue(txtSoTien));
            
            String result = db.postToFile(f, txtError, params, "HOADON/Add");
            if (result.contains("success")) {
                showSuccess("Thêm hóa đơn thành công!");
                loadData();
                clearForm();
            }
        } catch (Exception e) {
            showError("Lỗi thêm hóa đơn: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblResult.getSelectedRow() == -1) {
            showError("Vui lòng chọn hóa đơn cần sửa!");
            return;
        }
        
        if (!validateForm()) return;
        
        try {
            File f = new File("D:/IP_HoaDon.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHDN", getFieldValue(txtSoHDN));
            params.put("thang", getFieldValue(txtThang));
            params.put("nam", getFieldValue(txtNam));
            params.put("soHD", getFieldValue(txtSoHD));
            params.put("maNV", getFieldValue(txtMaNV));
            params.put("soTien", getFieldValue(txtSoTien));
            
            String result = db.postToFile(f, txtError, params, "HOADON/Update");
            if (result.contains("success")) {
                showSuccess("Cập nhật hóa đơn thành công!");
                loadData();
                clearForm();
            }
        } catch (Exception e) {
            showError("Lỗi cập nhật hóa đơn: " + e.getMessage());
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblResult.getSelectedRow() == -1) {
            showError("Vui lòng chọn hóa đơn cần xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa hóa đơn này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm != JOptionPane.YES_OPTION) return;
        
        try {
            File f = new File("D:/IP_HoaDon.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHDN", getFieldValue(txtSoHDN));
            
            String result = db.postToFile(f, txtError, params, "HOADON/Delete");
            if (result.contains("success")) {
                showSuccess("Xóa hóa đơn thành công!");
                loadData();
                clearForm();
            }
        } catch (Exception e) {
            showError("Lỗi xóa hóa đơn: " + e.getMessage());
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        clearForm();
        showInfo("Form đã được làm mới!");
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn đóng cửa sổ?", 
            "Xác nhận đóng", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    private boolean validateForm() {
        if (getFieldValue(txtSoHDN).isEmpty()) {
            showError("Vui lòng nhập số hóa đơn!");
            txtSoHDN.requestFocus();
            return false;
        }
        if (getFieldValue(txtThang).isEmpty()) {
            showError("Vui lòng nhập tháng!");
            txtThang.requestFocus();
            return false;
        }
        if (getFieldValue(txtNam).isEmpty()) {
            showError("Vui lòng nhập năm!");
            txtNam.requestFocus();
            return false;
        }
        if (getFieldValue(txtSoHD).isEmpty()) {
            showError("Vui lòng nhập số hợp đồng!");
            txtSoHD.requestFocus();
            return false;
        }
        if (getFieldValue(txtMaNV).isEmpty()) {
            showError("Vui lòng nhập mã nhân viên!");
            txtMaNV.requestFocus();
            return false;
        }
        if (getFieldValue(txtSoTien).isEmpty()) {
            showError("Vui lòng nhập số tiền!");
            txtSoTien.requestFocus();
            return false;
        }
        
        // Validate number formats
        try {
            Integer.parseInt(getFieldValue(txtThang));
            Integer.parseInt(getFieldValue(txtNam));
            Double.parseDouble(getFieldValue(txtSoTien));
        } catch (NumberFormatException e) {
            showError("Tháng, năm và số tiền phải là số hợp lệ!");
            return false;
        }
        
        return true;
    }

    private String getFieldValue(JTextField field) {
        String text = field.getText().trim();
        // Check if it's placeholder text
        if (text.equals("Nhập số hóa đơn...") || 
            text.equals("Tháng (1-12)...") ||
            text.equals("Năm...") ||
            text.equals("Nhập số hợp đồng...") ||
            text.equals("Nhập mã nhân viên...") ||
            text.equals("Nhập số tiền...")) {
            return "";
        }
        return text;
    }

    private void clearForm() {
        txtSoHDN.setText("");
        txtThang.setText("");
        txtNam.setText("");
        txtSoHD.setText("");
        txtMaNV.setText("");
        txtSoTien.setText("");
        txtError.setText("");
        setPlaceholderTexts();
        isEditing = false;
        originalSoHDN = "";
    }

    private void showError(String message) {
        txtError.setForeground(Color.RED);
        txtError.setText(message);
    }

    private void showSuccess(String message) {
        txtError.setForeground(new Color(0, 128, 0));
        txtError.setText(message);
    }

    private void showInfo(String message) {
        txtError.setForeground(Color.BLUE);
        txtError.setText(message);
    }

    private void loadData() {
        DataModel db = new DataModel();
        String tenCot[] = {"Số HDN", "Tháng", "Năm", "Số HD", "Mã NV", "Số Tiền"};
        tblmodel = new DefaultTableModel(tenCot, 0);
        tblResult.setModel(tblmodel);
        
        try {
            db.getDataHoaDon(tblmodel, tblResult, txtError);
            showInfo("Tải dữ liệu thành công! Tổng số: " + tblmodel.getRowCount() + " bản ghi.");
        } catch (Exception e) {
            showError("Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    // Additional UI components
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    
    // ... rest of the component declarations

    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextArea txtError;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JTextField txtSoHDN;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtThang;
}