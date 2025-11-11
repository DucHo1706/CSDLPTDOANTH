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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HÓA ĐƠN
 * @author duc18
 */
public class HoaDon extends javax.swing.JPanel {

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
    private final Color DISABLED_BACKGROUND = new Color(240, 240, 240);

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
                    txtSoHD_HOADON.setText(tblHoaDon.getValueAt(row, 3).toString());
                    txtMaNV_HOADON.setText(tblHoaDon.getValueAt(row, 4).toString());
                    
                    // Cẩn thận với giá trị tiền/null
                    Object soTien = tblHoaDon.getValueAt(row, 5);
                    txtSoTien.setText(soTien == null ? "" : soTien.toString());
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
        
        txtSoHDN.setFont(textFieldFont);
        txtThang.setFont(textFieldFont);
        txtNam.setFont(textFieldFont);
        txtSoHD_HOADON.setFont(textFieldFont);
        txtMaNV_HOADON.setFont(textFieldFont);
        txtSoTien.setFont(textFieldFont);
        
        txtSoHDN.setBorder(textFieldBorder);
        txtThang.setBorder(textFieldBorder);
        txtNam.setBorder(textFieldBorder);
        txtSoHD_HOADON.setBorder(textFieldBorder);
        txtMaNV_HOADON.setBorder(textFieldBorder);
        txtSoTien.setBorder(textFieldBorder);

        // Style disabled field
        txtSoHDN.setBackground(DISABLED_BACKGROUND);
        txtSoHDN.setForeground(new Color(100, 100, 100));

        // Style buttons
        styleButton(btnLoadHOADON, new Color(52, 152, 219)); // Blue
        styleButton(btnAddHOADON, new Color(39, 174, 96));   // Green
        styleButton(btnUpdateHOADON, new Color(243, 156, 18)); // Orange
        styleButton(btnDeleteHOADON, new Color(231, 76, 60)); // Red

        // Style table
        tblHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblHoaDon.setRowHeight(25);
        tblHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblHoaDon.getTableHeader().setBackground(new Color(52, 73, 94));
        tblHoaDon.getTableHeader().setForeground(Color.WHITE);
        tblHoaDon.setShowGrid(true);
        tblHoaDon.setGridColor(new Color(236, 240, 241));

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
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSoHDN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtThang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoHD_HOADON = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaNV_HOADON = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoTien = new javax.swing.JTextField();
        btnLoadHOADON = new javax.swing.JButton();
        btnAddHOADON = new javax.swing.JButton();
        btnUpdateHOADON = new javax.swing.JButton();
        btnDeleteHOADON = new javax.swing.JButton();

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

        jLabel1.setText("Số HĐN (Auto):");
        txtSoHDN.setEditable(false);
        txtSoHDN.setFocusable(false);
        
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
                            .addComponent(txtSoHD_HOADON)
                            .addComponent(txtMaNV_HOADON)
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
                    .addComponent(txtSoHD_HOADON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaNV_HOADON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadHOADONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadHOADONActionPerformed
        String endpoint = "/HOADON/Index";
        txtLogOutput.setText("Đang tải dữ liệu Hóa Đơn từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.getDataFromAllSites(ipFile, tblHoaDon, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadHOADONActionPerformed

    private void btnAddHOADONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHOADONActionPerformed
        String endpoint = "/HOADON/Add";
        
        Map<String, String> params = new HashMap<>();
        // Không gửi soHDN vì nó là IDENTITY (tự tăng)
        params.put("thang", txtThang.getText());
        params.put("nam", txtNam.getText());
        params.put("soHD", txtSoHD_HOADON.getText());
        params.put("maNV", txtMaNV_HOADON.getText());
        params.put("soTien", txtSoTien.getText());

        if (params.get("soHD").isEmpty() || params.get("maNV").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số HĐ và Mã NV không được trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Hóa Đơn đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadHOADONActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnAddHOADONActionPerformed

    private void btnUpdateHOADONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHOADONActionPerformed
        String endpoint = "/HOADON/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHDN", txtSoHDN.getText()); // Khóa chính để Update
        params.put("thang", txtThang.getText());
        params.put("nam", txtNam.getText());
        params.put("soHD", txtSoHD_HOADON.getText());
        params.put("maNV", txtMaNV_HOADON.getText());
        params.put("soTien", txtSoTien.getText());

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
    }//GEN-LAST:event_btnUpdateHOADONActionPerformed

    private void btnDeleteHOADONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHOADONActionPerformed
        String endpoint = "/HOADON/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("soHDN", txtSoHDN.getText());

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
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnDeleteHOADONActionPerformed

    /**
     * Hàm tiện ích để bật/tắt tất cả các nút (chạy trên luồng giao diện)
     */
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddHOADON;
    private javax.swing.JButton btnDeleteHOADON;
    private javax.swing.JButton btnLoadHOADON;
    private javax.swing.JButton btnUpdateHOADON;
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
    private javax.swing.JTextField txtMaNV_HOADON;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtSoHDN;
    private javax.swing.JTextField txtSoHD_HOADON;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}