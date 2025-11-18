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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HÓA ĐƠN
 * @author duc18
 */
public class HoaDon extends javax.swing.JPanel {

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
     * Creates new form HoaDonPanel
     */
    public HoaDon() {
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
        
       dataModel.loadComboBoxData(cmbSoHD_HOADON, txtLogOutput, "/HOPDONG/Index");
       dataModel.loadComboBoxData(cmbMaNV_HOADON, txtLogOutput, "/NHANVIEN/Index");
    }
    
    /**
     * Hàm tự động điền dữ liệu khi click vào bảng
     */
    private void addTableListeners() {
        tblHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblHoaDon.getSelectedRow() != -1) {
                    int row = tblHoaDon.getSelectedRow();
                    txtSoHDN.setText(tblHoaDon.getValueAt(row, 0).toString());
                    txtThang.setText(tblHoaDon.getValueAt(row, 1).toString());
                    txtNam.setText(tblHoaDon.getValueAt(row, 2).toString());
                    
                    // Đặt giá trị cho ComboBox
                    cmbSoHD_HOADON.setSelectedItem(tblHoaDon.getValueAt(row, 3).toString());
                    cmbMaNV_HOADON.setSelectedItem(tblHoaDon.getValueAt(row, 4).toString());
                    
                    Object soTien = tblHoaDon.getValueAt(row, 5);
                    txtSoTien.setText(soTien == null ? "" : soTien.toString());
                }
            }
        });
    }

    // Hàm reset form
    private void resetForm() {
        txtSoHDN.setText("");
        txtThang.setText("");
        txtNam.setText("");
        cmbSoHD_HOADON.setSelectedIndex(0);
        cmbMaNV_HOADON.setSelectedIndex(0);
        txtSoTien.setText("");
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
        
        txtSoHDN.setFont(textFieldFont);
        txtThang.setFont(textFieldFont);
        txtNam.setFont(textFieldFont);
        txtSoTien.setFont(textFieldFont);
        
        txtSoHDN.setBorder(textFieldBorder);
        txtThang.setBorder(textFieldBorder);
        txtNam.setBorder(textFieldBorder);
        txtSoTien.setBorder(textFieldBorder);

        styleButton(btnLoadHOADON, new Color(52, 152, 219)); 
        styleButton(btnAddHOADON, new Color(39, 174, 96));   
        styleButton(btnUpdateHOADON, new Color(243, 156, 18)); 
        styleButton(btnDeleteHOADON, new Color(231, 76, 60)); 

        tblHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblHoaDon.setRowHeight(25);
        tblHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblHoaDon.getTableHeader().setBackground(new Color(52, 73, 94));
        tblHoaDon.getTableHeader().setForeground(Color.BLACK);
        tblHoaDon.setShowGrid(true);
        tblHoaDon.setGridColor(new Color(236, 240, 241));

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
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSoHDN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoTien = new javax.swing.JTextField();
        btnLoadHOADON = new javax.swing.JButton();
        btnAddHOADON = new javax.swing.JButton();
        btnUpdateHOADON = new javax.swing.JButton();
        btnDeleteHOADON = new javax.swing.JButton();
        cmbSoHD_HOADON = new javax.swing.JComboBox<>();
        cmbMaNV_HOADON = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(8);
        jSplitPane1.setResizeWeight(0.35);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), 
            "THÔNG TIN HÓA ĐƠN", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Segoe UI", Font.BOLD, 14), 
            new java.awt.Color(41, 128, 185)
        ));

        jLabel1.setText("Số HĐN:"); // Đã sửa: bỏ "(Auto)"
        jLabel2.setText("Tháng:");
        jLabel3.setText("Năm:");
        jLabel4.setText("Số HĐ (Hợp Đồng):");
        jLabel5.setText("Mã NV (Lập HĐ):");
        jLabel6.setText("Số Tiền:");

        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnLoadHOADON.setText("Tải Lại");
        btnLoadHOADON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadHOADONActionPerformed(evt);
            }
        });
        panelButtons.add(btnLoadHOADON);

        btnAddHOADON.setText("Thêm");
        btnAddHOADON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHOADONActionPerformed(evt);
            }
        });
        panelButtons.add(btnAddHOADON);

        btnUpdateHOADON.setText("Sửa");
        btnUpdateHOADON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHOADONActionPerformed(evt);
            }
        });
        panelButtons.add(btnUpdateHOADON);

        btnDeleteHOADON.setText("Xóa");
        btnDeleteHOADON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHOADONActionPerformed(evt);
            }
        });
        panelButtons.add(btnDeleteHOADON);

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
                            .addComponent(txtSoHDN)
                            .addComponent(txtThang)
                            .addComponent(txtNam)
                            .addComponent(cmbSoHD_HOADON, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMaNV_HOADON, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoTien))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSoHDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbSoHD_HOADON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbMaNV_HOADON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelForm);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HĐN", "Tháng", "Năm", "Số HĐ", "Mã NV", "Số Tiền"
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
        jScrollPane1.setViewportView(tblHoaDon);

        jSplitPane1.setRightComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }

    private void btnLoadHOADONActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOADON/Index";
        txtLogOutput.setText("Đang tải dữ liệu Hóa Đơn từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.fetchAllSitesData(ipFile, tblHoaDon, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }

    private void btnAddHOADONActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOADON/Add";
        
        Map<String, String> params = new HashMap<>();
        // Gửi số hóa đơn do người dùng nhập
        params.put("soHDN", txtSoHDN.getText().trim());
        params.put("thang", txtThang.getText().trim());
        params.put("nam", txtNam.getText().trim());
        
        // Lấy dữ liệu từ ComboBox
        String soHD = "";
        if (cmbSoHD_HOADON.getSelectedItem() != null) {
            soHD = cmbSoHD_HOADON.getSelectedItem().toString();
        }
        
        String maNV = "";
        if (cmbMaNV_HOADON.getSelectedItem() != null) {
            maNV = cmbMaNV_HOADON.getSelectedItem().toString();
        }
        
        params.put("soHD", soHD);
        params.put("maNV", maNV);
        params.put("soTien", txtSoTien.getText().trim());

        // Kiểm tra dữ liệu đầu vào
        if (params.get("soHDN").isEmpty() || params.get("thang").isEmpty() || 
            params.get("nam").isEmpty() || params.get("soHD").isEmpty() ||
            params.get("maNV").isEmpty() || params.get("soTien").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin hóa đơn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate số hóa đơn
        if (!params.get("soHDN").matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Số hóa đơn chỉ được chứa chữ cái và số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate tháng, năm và số tiền là số
        try {
            Integer.parseInt(params.get("thang"));
            Integer.parseInt(params.get("nam"));
            Double.parseDouble(params.get("soTien"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tháng, Năm và Số Tiền phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Hóa Đơn đến MÁY CHÍNH...\n");
        txtLogOutput.append("Số HĐN: " + params.get("soHDN") + "\n");
        txtLogOutput.append("Tháng: " + params.get("thang") + "\n");
        txtLogOutput.append("Năm: " + params.get("nam") + "\n");
        txtLogOutput.append("Số HĐ: " + params.get("soHD") + "\n");
        txtLogOutput.append("Mã NV: " + params.get("maNV") + "\n");
        txtLogOutput.append("Số Tiền: " + params.get("soTien") + "\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            // Gọi API với số hóa đơn do người dùng nhập
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\n✅ THÊM THÀNH CÔNG! Đang tải lại dữ liệu...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHOADONActionPerformed(null);
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

    private void btnUpdateHOADONActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOADON/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHDN", txtSoHDN.getText().trim());
        params.put("thang", txtThang.getText().trim());
        params.put("nam", txtNam.getText().trim());

        // Lấy dữ liệu từ ComboBox
        String soHD = "";
        if (cmbSoHD_HOADON.getSelectedItem() != null) {
            soHD = cmbSoHD_HOADON.getSelectedItem().toString();
        }
        
        String maNV = "";
        if (cmbMaNV_HOADON.getSelectedItem() != null) {
            maNV = cmbMaNV_HOADON.getSelectedItem().toString();
        }

        params.put("soHD", soHD);
        params.put("maNV", maNV);
        params.put("soTien", txtSoTien.getText().trim());

        if (params.get("soHDN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Hóa Đơn (Số HĐN) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Hóa Đơn đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHOADONActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> SỬA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    private void btnDeleteHOADONActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/HOADON/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHDN", txtSoHDN.getText().trim());

        if (params.get("soHDN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Hóa Đơn (Số HĐN) để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa Hóa Đơn số: " + params.get("soHDN") + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh XÓA Hóa Đơn đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHOADONActionPerformed(null);
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
            btnLoadHOADON.setEnabled(enabled);
            btnAddHOADON.setEnabled(enabled);
            btnUpdateHOADON.setEnabled(enabled);
            btnDeleteHOADON.setEnabled(enabled);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnLoadHOADON.setEnabled(enabled);
                btnAddHOADON.setEnabled(enabled);
                btnUpdateHOADON.setEnabled(enabled);
                btnDeleteHOADON.setEnabled(enabled);
            });
        }
    }

    // Variables declaration
    private javax.swing.JButton btnAddHOADON;
    private javax.swing.JButton btnDeleteHOADON;
    private javax.swing.JButton btnLoadHOADON;
    private javax.swing.JButton btnUpdateHOADON;
    private javax.swing.JComboBox<String> cmbMaNV_HOADON;
    private javax.swing.JComboBox<String> cmbSoHD_HOADON;
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
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtSoHDN;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtThang;
}