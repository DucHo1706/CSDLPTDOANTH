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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB KHÁCH HÀNG
 * @author duc18
 */
public class KhachHang extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC === (Giữ nguyên)
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    // Colors (Giữ nguyên)
    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color PANEL_BACKGROUND = Color.WHITE;
    private final Color BORDER_COLOR = new Color(189, 195, 199);
    private final Color DISABLED_BACKGROUND = new Color(240, 240, 240); // Thêm màu này

    public KhachHang() {
        initComponents();
        addTableListeners();
        applyModernStyling();
    }

    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;
        
        // === THÊM MỚI: Tải dữ liệu cho ComboBox khi mở tab ===
        dataModel.loadComboBoxData(cmbMaCN_KH, txtLogOutput, "/CHINHANH/Index");
    }

    private void addTableListeners() {
        tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblKhachHang.getSelectedRow() != -1) {
                    int row = tblKhachHang.getSelectedRow();
                    txtMaKH.setText(tblKhachHang.getValueAt(row, 0).toString());
                    txtTenKH.setText(tblKhachHang.getValueAt(row, 1).toString());
                    
                    // === SỬA: Đặt giá trị cho ComboBox ===
                    cmbMaCN_KH.setSelectedItem(tblKhachHang.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void applyModernStyling() {
        // ... (Giữ nguyên code) ...
        setBackground(BACKGROUND_COLOR);
        panelForm.setBackground(PANEL_BACKGROUND);
        panelButtons.setBackground(PANEL_BACKGROUND);
        jScrollPane1.getViewport().setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        jLabel1.setFont(labelFont);
        jLabel2.setFont(labelFont);
        jLabel3.setFont(labelFont);
        
        jLabel1.setForeground(new Color(44, 62, 80));
        jLabel2.setForeground(new Color(44, 62, 80));
        jLabel3.setForeground(new Color(44, 62, 80));

        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );
        
        txtMaKH.setFont(textFieldFont);
        txtTenKH.setFont(textFieldFont);
        // cmbMaCN_KH.setFont(textFieldFont); // Style ComboBox
        
        txtMaKH.setBorder(textFieldBorder);
        txtTenKH.setBorder(textFieldBorder);
        // cmbMaCN_KH.setBorder(textFieldBorder);

        // === SỬA: Thêm style cho ô khóa chính bị vô hiệu hóa ===
        txtMaKH.setBackground(DISABLED_BACKGROUND);
        txtMaKH.setForeground(new Color(100, 100, 100));

        styleButton(btnLoadKH, new Color(52, 152, 219)); 
        styleButton(btnAddKH, new Color(39, 174, 96));   
        styleButton(btnUpdateKH, new Color(243, 156, 18)); 
        styleButton(btnDeleteKH, new Color(231, 76, 60)); 

        // ... (Giữ nguyên phần còn lại của hàm) ...
        tblKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblKhachHang.getTableHeader().setBackground(new Color(52, 73, 94));
        tblKhachHang.getTableHeader().setForeground(Color.BLACK);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.setGridColor(new Color(236, 240, 241));

        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        jSplitPane1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jSplitPane1.setBackground(BACKGROUND_COLOR);
    }

    // (Hàm styleButton giữ nguyên)
    private void styleButton(JButton button, Color color) {
        // ... (Giữ nguyên code) ...
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panelForm = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        btnLoadKH = new javax.swing.JButton();
        btnAddKH = new javax.swing.JButton();
        btnUpdateKH = new javax.swing.JButton();
        btnDeleteKH = new javax.swing.JButton();
        
        // === SỬA: Khởi tạo ComboBox ===
        // (Bạn phải làm điều này trong Design View)
        cmbMaCN_KH = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(8);
        jSplitPane1.setResizeWeight(0.3);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), 
            "THÔNG TIN KHÁCH HÀNG", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Segoe UI", Font.BOLD, 14), 
            new java.awt.Color(41, 128, 185)
        ));

        jLabel1.setText("Mã Khách Hàng (Auto):"); // Sửa Text
        txtMaKH.setEditable(false); // Sửa: Thêm dòng này
        txtMaKH.setFocusable(false); // Sửa: Thêm dòng này
        
        jLabel2.setText("Tên Khách Hàng:");
        jLabel3.setText("Mã Chi Nhánh:");

        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnLoadKH.setText("Tải Lại");
        btnLoadKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadKHActionPerformed(evt);
            }
        });
        panelButtons.add(btnLoadKH);

        btnAddKH.setText("Thêm");
        btnAddKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddKHActionPerformed(evt);
            }
        });
        panelButtons.add(btnAddKH);

        btnUpdateKH.setText("Sửa");
        btnUpdateKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKHActionPerformed(evt);
            }
        });
        panelButtons.add(btnUpdateKH);

        btnDeleteKH.setText("Xóa");
        btnDeleteKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteKHActionPerformed(evt);
            }
        });
        panelButtons.add(btnDeleteKH);

        // === SỬA: Bố cục Layout để chứa ComboBox ===
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
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH)
                            .addComponent(txtTenKH)
                            .addComponent(cmbMaCN_KH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))) // Sửa
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbMaCN_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Sửa
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // === HẾT SỬA BỐ CỤC ===

        jSplitPane1.setLeftComponent(panelForm);

        // (Model Bảng giữ nguyên)
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Mã CN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jSplitPane1.setRightComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // (Hàm btnLoadKHActionPerformed giữ nguyên)
    private void btnLoadKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Index";
        txtLogOutput.setText("Đang tải dữ liệu Khách Hàng từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.fetchAllSitesData(ipFile, tblKhachHang, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }

    // === SỬA HÀM ADD ===
    private void btnAddKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Add";
        
        Map<String, String> params = new HashMap<>();
        // Không gửi maKH (SP sẽ tự tạo)
        params.put("tenKH", txtTenKH.getText());
        
        // Lấy maCN từ ComboBox
        String maCN = "";
        if (cmbMaCN_KH.getSelectedItem() != null) {
            maCN = cmbMaCN_KH.getSelectedItem().toString();
        }
        params.put("maCN", maCN);


        if (params.get("tenKH").isEmpty() || params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên KH và chọn Chi Nhánh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Khách Hàng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            // Gọi API (API sẽ gọi sp_TaoKhachHangTuDong)
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadKHActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    // === SỬA HÀM UPDATE ===
    private void btnUpdateKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("maKH", txtMaKH.getText()); // Khóa chính (string)
        params.put("tenKH", txtTenKH.getText());
        
        // Lấy maCN từ ComboBox
        String maCN = "";
        if (cmbMaCN_KH.getSelectedItem() != null) {
            maCN = cmbMaCN_KH.getSelectedItem().toString();
        }
        params.put("maCN", maCN);

        if (params.get("maKH").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Khách Hàng (Mã KH) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Khách Hàng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadKHActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> SỬA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }

    // === SỬA HÀM DELETE ===
    private void btnDeleteKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("maKH", txtMaKH.getText()); // Khóa chính (string)

        if (params.get("maKH").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Khách Hàng (Mã KH) để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa Khách Hàng: " + params.get("maKH") + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh XÓA Khách Hàng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadKHActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }
    
    // (Hàm setAllButtonsEnabled giữ nguyên)
    private void setAllButtonsEnabled(boolean enabled) {
        // ... (Giữ nguyên code) ...
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            btnLoadKH.setEnabled(enabled);
            btnAddKH.setEnabled(enabled);
            btnUpdateKH.setEnabled(enabled);
            btnDeleteKH.setEnabled(enabled);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnLoadKH.setEnabled(enabled);
                btnAddKH.setEnabled(enabled);
                btnUpdateKH.setEnabled(enabled);
                btnDeleteKH.setEnabled(enabled);
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddKH;
    private javax.swing.JButton btnDeleteKH;
    private javax.swing.JButton btnLoadKH;
    private javax.swing.JButton btnUpdateKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTenKH;
    
    // === SỬA: Khai báo ComboBox ===
    private javax.swing.JComboBox<String> cmbMaCN_KH;
    // End of variables declaration//GEN-END:variables
}