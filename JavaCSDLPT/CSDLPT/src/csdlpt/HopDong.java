package csdlpt;

// Import (Giữ nguyên)
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HỢP ĐỒNG
 * @author duc18
 */
public class HopDong extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC === (Giữ nguyên)
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    // Colors for modern UI (Giữ nguyên)
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color PANEL_BACKGROUND = Color.WHITE;
    private final Color BORDER_COLOR = new Color(189, 195, 199);

    /**
     * Creates new form HopDongPanel
     */
    public HopDong() {
        initComponents(); 
        addTableListeners();
        applyModernStyling();
    }

    /**
     * Hàm QUAN TRỌNG: MainForm sẽ gọi hàm này
     */
    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;
        
        dataModel.loadComboBoxData(cmbMaKH_HD, txtLogOutput, "/KHACHHANG/Index");
    }
    
    /**
     * Hàm tự động điền dữ liệu khi click vào bảng
     */
    private void addTableListeners() {
        tblHopDong.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblHopDong.getSelectedRow() != -1) {
                    int row = tblHopDong.getSelectedRow();
                    txtSoHD.setText(tblHopDong.getValueAt(row, 0).toString());
                    txtNgayKy.setText(tblHopDong.getValueAt(row, 1).toString());
                    
                    // Đặt giá trị cho ComboBox
                    cmbMaKH_HD.setSelectedItem(tblHopDong.getValueAt(row, 2).toString());
                    
                    txtSoDienKe.setText(tblHopDong.getValueAt(row, 3).toString());
                    txtKWDinhMuc.setText(tblHopDong.getValueAt(row, 4).toString());
                    
                    Object donGia = tblHopDong.getValueAt(row, 5);
                    txtDonGiaKW.setText(donGia == null ? "" : donGia.toString());
                }
            }
        });
    }

    // Hàm reset form
    private void resetForm() {
        txtSoHD.setText("");
        txtNgayKy.setText("");
        cmbMaKH_HD.setSelectedIndex(0);
        txtSoDienKe.setText("");
        txtKWDinhMuc.setText("");
        txtDonGiaKW.setText("");
    }

    private void applyModernStyling() {
        setBackground(BACKGROUND_COLOR);
        panelForm.setBackground(PANEL_BACKGROUND);
        panelButtons.setBackground(PANEL_BACKGROUND);
        jScrollPane1.getViewport().setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        jLabel3.setFont(labelFont);
        jLabel4.setFont(labelFont);
        jLabel5.setFont(labelFont);
        jLabel6.setFont(labelFont);
        
        jLabel1.setForeground(new Color(44, 62, 80));
        jLabel2.setForeground(new Color(44, 62, 80));
        jLabel3.setForeground(new Color(44, 62, 80));
        jLabel4.setForeground(new Color(44, 62, 80));
        jLabel5.setForeground(new Color(44, 62, 80));
        jLabel6.setForeground(new Color(44, 62, 80));

        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );
        
        txtSoHD.setFont(textFieldFont);
        txtNgayKy.setFont(textFieldFont);
        txtSoDienKe.setFont(textFieldFont);
        txtKWDinhMuc.setFont(textFieldFont);
        txtDonGiaKW.setFont(textFieldFont);
        
        txtSoHD.setBorder(textFieldBorder);
        txtNgayKy.setBorder(textFieldBorder);
        txtSoDienKe.setBorder(textFieldBorder);
        txtKWDinhMuc.setBorder(textFieldBorder);
        txtDonGiaKW.setBorder(textFieldBorder);

        styleButton(btnLoadHD, new Color(52, 152, 219));
        styleButton(btnAddHD, new Color(39, 174, 96));
        styleButton(btnUpdateHD, new Color(243, 156, 18));
        styleButton(btnDeleteHD, new Color(231, 76, 60));

        tblHopDong.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblHopDong.setRowHeight(25);
        tblHopDong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblHopDong.getTableHeader().setBackground(new Color(52, 73, 94));
        tblHopDong.getTableHeader().setForeground(Color.BLACK);
        tblHopDong.setShowGrid(true);
        tblHopDong.setGridColor(new Color(236, 240, 241));

        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        jSplitPane1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jSplitPane1.setBackground(BACKGROUND_COLOR);
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(90, 35));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panelForm = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHopDong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgayKy = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoDienKe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtKWDinhMuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDonGiaKW = new javax.swing.JTextField();
        btnLoadHD = new javax.swing.JButton();
        btnAddHD = new javax.swing.JButton();
        btnUpdateHD = new javax.swing.JButton();
        btnDeleteHD = new javax.swing.JButton();
        cmbMaKH_HD = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(8);
        jSplitPane1.setResizeWeight(0.35);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), 
            "THÔNG TIN HỢP ĐỒNG", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Segoe UI", Font.BOLD, 14), 
            new java.awt.Color(41, 128, 185)
        ));

        jLabel1.setText("Số Hợp Đồng:"); // Đã sửa: bỏ "(Auto)"
        jLabel2.setText("Ngày Ký (yyyy-mm-dd):");
        jLabel3.setText("Mã Khách Hàng:");
        jLabel4.setText("Số Điện Kế:");
        jLabel5.setText("KW Định Mức:");
        jLabel6.setText("Đơn Giá KW:");

        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnLoadHD.setText("Tải Lại");
        btnLoadHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadHDActionPerformed(evt);
            }
        });
        panelButtons.add(btnLoadHD);

        btnAddHD.setText("Thêm");
        btnAddHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHDActionPerformed(evt);
            }
        });
        panelButtons.add(btnAddHD);

        btnUpdateHD.setText("Sửa");
        btnUpdateHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHDActionPerformed(evt);
            }
        });
        panelButtons.add(btnUpdateHD);

        btnDeleteHD.setText("Xóa");
        btnDeleteHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHDActionPerformed(evt);
            }
        });
        panelButtons.add(btnDeleteHD);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoHD)
                            .addComponent(txtNgayKy)
                            .addComponent(cmbMaKH_HD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoDienKe)
                            .addComponent(txtKWDinhMuc)
                            .addComponent(txtDonGiaKW))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbMaKH_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoDienKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKWDinhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDonGiaKW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelForm);

        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HĐ", "Ngày Ký", "Mã KH", "Số Điện Kế", "KW Định Mức", "Đơn Giá KW"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHopDong);

        jSplitPane1.setRightComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }

    private void btnLoadHDActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOPDONG/Index";
        txtLogOutput.setText("Đang tải dữ liệu Hợp Đồng từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.fetchAllSitesData(ipFile, tblHopDong, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }

    private void btnAddHDActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOPDONG/Add";
        
        Map<String, String> params = new HashMap<>();
        // Gửi số hợp đồng do người dùng nhập
        params.put("soHD", txtSoHD.getText().trim());
        params.put("ngayKy", txtNgayKy.getText().trim());
        
        // Lấy maKH từ ComboBox
        String maKH = "";
        if (cmbMaKH_HD.getSelectedItem() != null) {
            maKH = cmbMaKH_HD.getSelectedItem().toString();
        }
        params.put("maKH", maKH);
        
        params.put("soDienKe", txtSoDienKe.getText().trim());
        params.put("kwDinhMuc", txtKWDinhMuc.getText().trim());
        params.put("dongiaKW", txtDonGiaKW.getText().trim());

        // Kiểm tra dữ liệu đầu vào
        if (params.get("soHD").isEmpty() || params.get("ngayKy").isEmpty() || 
            params.get("maKH").isEmpty() || params.get("soDienKe").isEmpty() ||
            params.get("kwDinhMuc").isEmpty() || params.get("dongiaKW").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin hợp đồng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate số hợp đồng
        if (!params.get("soHD").matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Số hợp đồng chỉ được chứa chữ cái và số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate số KW và đơn giá là số
        try {
            Integer.parseInt(params.get("kwDinhMuc"));
            Double.parseDouble(params.get("dongiaKW"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "KW Định Mức và Đơn Giá KW phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Hợp Đồng đến MÁY CHÍNH...\n");
        txtLogOutput.append("Số HĐ: " + params.get("soHD") + "\n");
        txtLogOutput.append("Ngày Ký: " + params.get("ngayKy") + "\n");
        txtLogOutput.append("Mã KH: " + params.get("maKH") + "\n");
        txtLogOutput.append("Số Điện Kế: " + params.get("soDienKe") + "\n");
        txtLogOutput.append("KW Định Mức: " + params.get("kwDinhMuc") + "\n");
        txtLogOutput.append("Đơn Giá KW: " + params.get("dongiaKW") + "\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            // Gọi API với số hợp đồng do người dùng nhập
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\n✅ THÊM THÀNH CÔNG! Đang tải lại dữ liệu...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHDActionPerformed(null);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    resetForm(); // Reset form sau khi thêm thành công
                });
            } else {
                txtLogOutput.append("\n❌ THÊM THẤT BẠI.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void btnUpdateHDActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOPDONG/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHD", txtSoHD.getText().trim());
        params.put("ngayKy", txtNgayKy.getText().trim());
        
        // Lấy maKH từ ComboBox
        String maKH = "";
        if (cmbMaKH_HD.getSelectedItem() != null) {
            maKH = cmbMaKH_HD.getSelectedItem().toString();
        }
        params.put("maKH", maKH);
        
        params.put("soDienKe", txtSoDienKe.getText().trim());
        params.put("kwDinhMuc", txtKWDinhMuc.getText().trim());
        params.put("dongiaKW", txtDonGiaKW.getText().trim());

        if (params.get("soHD").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Hợp Đồng (Số HĐ) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Hợp Đồng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHDActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> SỬA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void btnDeleteHDActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOPDONG/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHD", txtSoHD.getText().trim());

        if (params.get("soHD").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Hợp Đồng (Số HĐ) để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa Hợp Đồng: " + params.get("soHD") + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh XÓA Hợp Đồng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHDActionPerformed(null);
                javax.swing.SwingUtilities.invokeLater(() -> {
                    resetForm(); // Reset form sau khi xóa thành công
                });
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void setAllButtonsEnabled(boolean enabled) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            btnLoadHD.setEnabled(enabled);
            btnAddHD.setEnabled(enabled);
            btnUpdateHD.setEnabled(enabled);
            btnDeleteHD.setEnabled(enabled);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnLoadHD.setEnabled(enabled);
                btnAddHD.setEnabled(enabled);
                btnUpdateHD.setEnabled(enabled);
                btnDeleteHD.setEnabled(enabled);
            });
        }
    }

    // Variables declaration
    private javax.swing.JButton btnAddHD;
    private javax.swing.JButton btnDeleteHD;
    private javax.swing.JButton btnLoadHD;
    private javax.swing.JButton btnUpdateHD;
    private javax.swing.JComboBox<String> cmbMaKH_HD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTextField txtDonGiaKW;
    private javax.swing.JTextField txtKWDinhMuc;
    private javax.swing.JTextField txtNgayKy;
    private javax.swing.JTextField txtSoDienKe;
    private javax.swing.JTextField txtSoHD;
}