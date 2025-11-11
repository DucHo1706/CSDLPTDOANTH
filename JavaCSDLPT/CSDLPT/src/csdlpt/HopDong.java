package csdlpt;

// Import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HỢP ĐỒNG
 * @author duc18
 */
public class HopDong extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC ===
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    // Colors for modern UI
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
                    txtMaKH_HD.setText(tblHopDong.getValueAt(row, 2).toString());
                    txtSoDienKe.setText(tblHopDong.getValueAt(row, 3).toString());
                    txtKWDinhMuc.setText(tblHopDong.getValueAt(row, 4).toString());
                    // Cẩn thận với giá trị tiền/null
                    Object donGia = tblHopDong.getValueAt(row, 5);
                    txtDonGiaKW.setText(donGia == null ? "" : donGia.toString());
                }
            }
        });
    }

    private void applyModernStyling() {
        // Set background
        setBackground(BACKGROUND_COLOR);
        panelForm.setBackground(PANEL_BACKGROUND);
        panelButtons.setBackground(PANEL_BACKGROUND);
        jScrollPane1.getViewport().setBackground(Color.WHITE);

        // Style labels
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

        // Style text fields
        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );
        
        txtSoHD.setFont(textFieldFont);
        txtNgayKy.setFont(textFieldFont);
        txtMaKH_HD.setFont(textFieldFont);
        txtSoDienKe.setFont(textFieldFont);
        txtKWDinhMuc.setFont(textFieldFont);
        txtDonGiaKW.setFont(textFieldFont);
        
        txtSoHD.setBorder(textFieldBorder);
        txtNgayKy.setBorder(textFieldBorder);
        txtMaKH_HD.setBorder(textFieldBorder);
        txtSoDienKe.setBorder(textFieldBorder);
        txtKWDinhMuc.setBorder(textFieldBorder);
        txtDonGiaKW.setBorder(textFieldBorder);

        // Style buttons
        styleButton(btnLoadHD, new Color(52, 152, 219)); // Blue
        styleButton(btnAddHD, new Color(39, 174, 96));   // Green
        styleButton(btnUpdateHD, new Color(243, 156, 18)); // Orange
        styleButton(btnDeleteHD, new Color(231, 76, 60)); // Red

        // Style table
        tblHopDong.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblHopDong.setRowHeight(25);
        tblHopDong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblHopDong.getTableHeader().setBackground(new Color(52, 73, 94));
        tblHopDong.getTableHeader().setForeground(Color.WHITE);
        tblHopDong.setShowGrid(true);
        tblHopDong.setGridColor(new Color(236, 240, 241));

        // Style scroll pane
        jScrollPane1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // Style split pane
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
        
        // Hover effects
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
        tblHopDong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgayKy = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH_HD = new javax.swing.JTextField();
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

        jLabel1.setText("Số Hợp Đồng:");
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
                            .addComponent(txtMaKH_HD)
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
                    .addComponent(txtMaKH_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadHDActionPerformed
        String endpoint = "/HOPDONG/Index";
        txtLogOutput.setText("Đang tải dữ liệu Hợp Đồng từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.getDataFromAllSites(ipFile, tblHopDong, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadHDActionPerformed

    private void btnAddHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHDActionPerformed
        String endpoint = "/HOPDONG/Add";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHD", txtSoHD.getText());
        params.put("ngayKy", txtNgayKy.getText());
        params.put("maKH", txtMaKH_HD.getText());
        params.put("soDienKe", txtSoDienKe.getText());
        params.put("kwDinhMuc", txtKWDinhMuc.getText());
        params.put("dongiaKW", txtDonGiaKW.getText());

        if (params.get("soHD").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Số HĐ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Hợp Đồng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHDActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnAddHDActionPerformed

    private void btnUpdateHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHDActionPerformed
        String endpoint = "/HOPDONG/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHD", txtSoHD.getText());
        params.put("ngayKy", txtNgayKy.getText());
        params.put("maKH", txtMaKH_HD.getText());
        params.put("soDienKe", txtSoDienKe.getText());
        params.put("kwDinhMuc", txtKWDinhMuc.getText());
        params.put("dongiaKW", txtDonGiaKW.getText());

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
    }//GEN-LAST:event_btnUpdateHDActionPerformed

    private void btnDeleteHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHDActionPerformed
        String endpoint = "/HOPDONG/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHD", txtSoHD.getText());

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
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnDeleteHDActionPerformed

    /**
     * Hàm tiện ích để bật/tắt tất cả các nút (chạy trên luồng giao diện)
     */
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHD;
    private javax.swing.JButton btnDeleteHD;
    private javax.swing.JButton btnLoadHD;
    private javax.swing.JButton btnUpdateHD;
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
    private javax.swing.JTextField txtMaKH_HD;
    private javax.swing.JTextField txtNgayKy;
    private javax.swing.JTextField txtSoDienKe;
    private javax.swing.JTextField txtSoHD;
    // End of variables declaration//GEN-END:variables
}