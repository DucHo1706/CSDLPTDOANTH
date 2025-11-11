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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB NHÂN VIÊN
 * @author duc18
 */
public class NhanVien extends javax.swing.JPanel {

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
                    txtMaCN_NV.setText(tblNhanVien.getValueAt(row, 2).toString());
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
        
        jLabel1.setForeground(new Color(44, 62, 80));
        jLabel2.setForeground(new Color(44, 62, 80));
        jLabel3.setForeground(new Color(44, 62, 80));

        // Style text fields
        Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        );
        
        txtMaNV.setFont(textFieldFont);
        txtTenNV.setFont(textFieldFont);
        txtMaCN_NV.setFont(textFieldFont);
        
        txtMaNV.setBorder(textFieldBorder);
        txtTenNV.setBorder(textFieldBorder);
        txtMaCN_NV.setBorder(textFieldBorder);

        // Style buttons
        styleButton(btnLoadNV, new Color(52, 152, 219)); // Blue
        styleButton(btnAddNV, new Color(39, 174, 96));   // Green
        styleButton(btnUpdateNV, new Color(243, 156, 18)); // Orange
        styleButton(btnDeleteNV, new Color(231, 76, 60)); // Red

        // Style table
        tblNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblNhanVien.setRowHeight(25);
        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblNhanVien.getTableHeader().setBackground(new Color(52, 73, 94));
        tblNhanVien.getTableHeader().setForeground(Color.WHITE);
        tblNhanVien.setShowGrid(true);
        tblNhanVien.setGridColor(new Color(236, 240, 241));

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
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaCN_NV = new javax.swing.JTextField();
        btnLoadNV = new javax.swing.JButton();
        btnAddNV = new javax.swing.JButton();
        btnUpdateNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();

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

        jLabel1.setText("Mã Nhân Viên:");
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
                            .addComponent(txtMaCN_NV))))
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
                    .addComponent(txtMaCN_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelForm);

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

    private void btnLoadNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadNVActionPerformed
        String endpoint = "/NHANVIEN/Index";
        txtLogOutput.setText("Đang tải dữ liệu Nhân Viên từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.getDataFromAllSites(ipFile, tblNhanVien, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadNVActionPerformed

    private void btnAddNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVActionPerformed
        String endpoint = "/NHANVIEN/Add";
        
        Map<String, String> params = new HashMap<>();
        params.put("maNV", txtMaNV.getText());
        params.put("tenNV", txtTenNV.getText());
        params.put("maCN", txtMaCN_NV.getText());

        if (params.get("maNV").isEmpty() || params.get("tenNV").isEmpty() || params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Nhân Viên đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
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

    private void btnUpdateNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNVActionPerformed
        String endpoint = "/NHANVIEN/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("maNV", txtMaNV.getText());
        params.put("tenNV", txtTenNV.getText());
        params.put("maCN", txtMaCN_NV.getText());

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

    private void btnDeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNVActionPerformed
        String endpoint = "/NHANVIEN/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("maNV", txtMaNV.getText());

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

    /**
     * Hàm tiện ích để bật/tắt tất cả các nút (chạy trên luồng giao diện)
     */
    private void setAllButtonsEnabled(boolean enabled) {
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
    private javax.swing.JTextField txtMaCN_NV;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}