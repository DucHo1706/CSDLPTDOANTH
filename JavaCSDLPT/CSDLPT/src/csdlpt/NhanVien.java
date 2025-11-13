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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB NHÂN VIÊN
 * @author duc18
 */
public class NhanVien extends javax.swing.JPanel {

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

    /**
     * Creates new form NhanVienPanel
     */
    public NhanVien() {
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
        
        // === THÊM MỚI: Tải dữ liệu cho ComboBox khi mở tab ===
        dataModel.loadComboBoxData(cmbMaCN_NV, txtLogOutput, "/CHINHANH/Index");
    }
    
    /**
     * Hàm tự động điền dữ liệu khi click vào bảng
     */
    private void addTableListeners() {
        tblNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblNhanVien.getSelectedRow() != -1) {
                    int row = tblNhanVien.getSelectedRow();
                    txtMaNV.setText(tblNhanVien.getValueAt(row, 0).toString());
                    txtTenNV.setText(tblNhanVien.getValueAt(row, 1).toString());
                    
                    // === SỬA: Đặt giá trị cho ComboBox ===
                    cmbMaCN_NV.setSelectedItem(tblNhanVien.getValueAt(row, 2).toString());
                }
            }
        });
    }

    // (Hàm applyModernStyling giữ nguyên, chỉ sửa 1 dòng)
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
        
        txtMaNV.setFont(textFieldFont);
        txtTenNV.setFont(textFieldFont);
        // cmbMaCN_NV.setFont(textFieldFont); // Style ComboBox
        
        txtMaNV.setBorder(textFieldBorder);
        txtTenNV.setBorder(textFieldBorder);
        // cmbMaCN_NV.setBorder(textFieldBorder);

        // === SỬA: Thêm style cho ô khóa chính bị vô hiệu hóa ===
        txtMaNV.setBackground(DISABLED_BACKGROUND);
        txtMaNV.setForeground(new Color(100, 100, 100));

        styleButton(btnLoadNV, new Color(52, 152, 219));
        styleButton(btnAddNV, new Color(39, 174, 96));
        styleButton(btnUpdateNV, new Color(243, 156, 18));
        styleButton(btnDeleteNV, new Color(231, 76, 60));

        // ... (Giữ nguyên phần còn lại của hàm) ...
        tblNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblNhanVien.setRowHeight(25);
        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblNhanVien.getTableHeader().setBackground(new Color(52, 73, 94));
        tblNhanVien.getTableHeader().setForeground(Color.BLACK);
        tblNhanVien.setShowGrid(true);
        tblNhanVien.setGridColor(new Color(236, 240, 241));

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
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLoadNV = new javax.swing.JButton();
        btnAddNV = new javax.swing.JButton();
        btnUpdateNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();
        
        // === SỬA: Khởi tạo ComboBox ===
        // (Bạn phải làm điều này trong Design View)
        cmbMaCN_NV = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(8);
        jSplitPane1.setResizeWeight(0.3);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), 
            "THÔNG TIN NHÂN VIÊN", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Segoe UI", Font.BOLD, 14), 
            new java.awt.Color(41, 128, 185)
        ));

        jLabel1.setText("Mã Nhân Viên (Auto):"); // Sửa Text
        txtMaNV.setEditable(false); // Sửa: Thêm dòng này
        txtMaNV.setFocusable(false); // Sửa: Thêm dòng này
        
        jLabel2.setText("Tên Nhân Viên:");
        jLabel3.setText("Mã Chi Nhánh:");

        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnLoadNV.setText("Tải Lại");
        btnLoadNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadNVActionPerformed(evt);
            }
        });
        panelButtons.add(btnLoadNV);

        btnAddNV.setText("Thêm");
        btnAddNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNVActionPerformed(evt);
            }
        });
        panelButtons.add(btnAddNV);

        btnUpdateNV.setText("Sửa");
        btnUpdateNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNVActionPerformed(evt);
            }
        });
        panelButtons.add(btnUpdateNV);

        btnDeleteNV.setText("Xóa");
        btnDeleteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNVActionPerformed(evt);
            }
        });
        panelButtons.add(btnDeleteNV);

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
                            .addComponent(txtMaNV)
                            .addComponent(txtTenNV)
                            .addComponent(cmbMaCN_NV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))) // Sửa
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbMaCN_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) // Sửa
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // === HẾT SỬA BỐ CỤC ===

        jSplitPane1.setLeftComponent(panelForm);

        // (Model Bảng giữ nguyên)
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "Mã CN"
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
        jScrollPane1.setViewportView(tblNhanVien);

        jSplitPane1.setRightComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // (Hàm btnLoadNVActionPerformed giữ nguyên)
    private void btnLoadNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadNVActionPerformed
        String endpoint = "/NHANVIEN/Index";
        txtLogOutput.setText("Đang tải dữ liệu Nhân Viên từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.fetchAllSitesData(ipFile, tblNhanVien, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadNVActionPerformed

    
    // === SỬA HÀM ADD ===
    private void btnAddNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVActionPerformed
        String endpoint = "/NHANVIEN/Add";
        
        Map<String, String> params = new HashMap<>();
        // Không gửi maNV (SP sẽ tự tạo)
        params.put("hoten", txtTenNV.getText());
        
        // Lấy maCN từ ComboBox
        String maCN = "";
        if (cmbMaCN_NV.getSelectedItem() != null) {
            maCN = cmbMaCN_NV.getSelectedItem().toString();
        }
        params.put("maCN", maCN);

        if (params.get("hoten").isEmpty() || params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Họ Tên và chọn Chi Nhánh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Nhân Viên đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            // Gọi API (API sẽ gọi sp_TaoNhanVienTuDong)
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadNVActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnAddNVActionPerformed

    
    // === SỬA HÀM UPDATE ===
    private void btnUpdateNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNVActionPerformed
        String endpoint = "/NHANVIEN/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("maNV", txtMaNV.getText()); // Khóa chính (string)
        params.put("hoten", txtTenNV.getText()); // Sửa: tên param là 'hoten'
        
        // Lấy maCN từ ComboBox
        String maCN = "";
        if (cmbMaCN_NV.getSelectedItem() != null) {
            maCN = cmbMaCN_NV.getSelectedItem().toString();
        }
        params.put("maCN", maCN);

        if (params.get("maNV").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Nhân Viên (Mã NV) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Nhân Viên đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadNVActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> SỬA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnUpdateNVActionPerformed

    
    // === SỬA HÀM DELETE ===
    private void btnDeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNVActionPerformed
        String endpoint = "/NHANVIEN/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("maNV", txtMaNV.getText()); // Khóa chính (string)

        if (params.get("maNV").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Nhân Viên (Mã NV) để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa Nhân Viên: " + params.get("maNV") + "?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh XÓA Nhân Viên đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadNVActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnDeleteNVActionPerformed

    // (Hàm setAllButtonsEnabled giữ nguyên)
    private void setAllButtonsEnabled(boolean enabled) {
        // ... (Giữ nguyên code) ...
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            btnLoadNV.setEnabled(enabled);
            btnAddNV.setEnabled(enabled);
            btnUpdateNV.setEnabled(enabled);
            btnDeleteNV.setEnabled(enabled);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnLoadNV.setEnabled(enabled);
                btnAddNV.setEnabled(enabled);
                btnUpdateNV.setEnabled(enabled);
                btnDeleteNV.setEnabled(enabled);
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNV;
    private javax.swing.JButton btnDeleteNV;
    private javax.swing.JButton btnLoadNV;
    private javax.swing.JButton btnUpdateNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTenNV;
    
    // === SỬA: Khai báo ComboBox ===
    private javax.swing.JComboBox<String> cmbMaCN_NV;
    // End of variables declaration//GEN-END:variables
}