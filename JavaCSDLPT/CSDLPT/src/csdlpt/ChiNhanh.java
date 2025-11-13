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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB CHI NHÁNH
 * @author duc18
 */
public class ChiNhanh extends javax.swing.JPanel {

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
    private final Color DISABLED_BACKGROUND = new Color(240, 240, 240); 

    /**
     * Creates new form ChiNhanhPanel
     */
    public ChiNhanh() {
        initComponents();
        addTableListeners();
        applyModernStyling();
    }

    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;
        // File này không cần tải ComboBox nào cả
    }

    private void addTableListeners() {
        tblChiNhanh.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblChiNhanh.getSelectedRow() != -1) {
                    int row = tblChiNhanh.getSelectedRow();
                    txtMaCN.setText(tblChiNhanh.getValueAt(row, 0).toString());
                    txtTenCN.setText(tblChiNhanh.getValueAt(row, 1).toString());
                    txtThanhPho.setText(tblChiNhanh.getValueAt(row, 2).toString());
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
        
        txtMaCN.setFont(textFieldFont);
        txtTenCN.setFont(textFieldFont);
        txtThanhPho.setFont(textFieldFont);
        
        txtMaCN.setBorder(textFieldBorder);
        txtTenCN.setBorder(textFieldBorder);
        txtThanhPho.setBorder(textFieldBorder);

        txtMaCN.setBackground(DISABLED_BACKGROUND);
        txtMaCN.setForeground(new Color(100, 100, 100));

        styleButton(btnLoadCN, new Color(52, 152, 219));
        styleButton(btnAddCN, new Color(39, 174, 96));
        styleButton(btnUpdateCN, new Color(243, 156, 18));
        styleButton(btnDeleteCN, new Color(231, 76, 60));

        tblChiNhanh.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblChiNhanh.setRowHeight(25);
        tblChiNhanh.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblChiNhanh.getTableHeader().setBackground(new Color(52, 73, 94));
        tblChiNhanh.getTableHeader().setForeground(Color.BLACK);
        tblChiNhanh.setShowGrid(true);
        tblChiNhanh.setGridColor(new Color(236, 240, 241));

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panelForm = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiNhanh = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaCN = new javax.swing.JTextField();
        txtTenCN = new javax.swing.JTextField();
        txtThanhPho = new javax.swing.JTextField();
        btnLoadCN = new javax.swing.JButton();
        btnAddCN = new javax.swing.JButton();
        btnUpdateCN = new javax.swing.JButton();
        btnDeleteCN = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerSize(8);
        jSplitPane1.setResizeWeight(0.3);

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5), 
            "THÔNG TIN CHI NHÁNH", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new java.awt.Font("Segoe UI", Font.BOLD, 14), 
            new java.awt.Color(41, 128, 185)
        ));

        jLabel1.setText("Mã Chi Nhánh (Auto):"); // Sửa Text
        txtMaCN.setEditable(false); // Sửa: Thêm dòng này
        txtMaCN.setFocusable(false); // Sửa: Thêm dòng này
        
        jLabel2.setText("Tên Chi Nhánh:");
        jLabel3.setText("Thành Phố:");

        panelButtons.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        btnLoadCN.setText("Tải Lại");
        btnLoadCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadCNActionPerformed(evt);
            }
        });
        panelButtons.add(btnLoadCN);

        btnAddCN.setText("Thêm");
        btnAddCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCNActionPerformed(evt);
            }
        });
        panelButtons.add(btnAddCN);

        btnUpdateCN.setText("Sửa");
        btnUpdateCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCNActionPerformed(evt);
            }
        });
        panelButtons.add(btnUpdateCN);

        btnDeleteCN.setText("Xóa");
        btnDeleteCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCNActionPerformed(evt);
            }
        });
        panelButtons.add(btnDeleteCN);

        // (Layout giữ nguyên, không cần ComboBox)
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
                            .addComponent(txtMaCN)
                            .addComponent(txtTenCN)
                            .addComponent(txtThanhPho))))
                .addContainerGap())
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(panelForm);

        // (Model Bảng giữ nguyên)
        tblChiNhanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CN", "Tên CN", "Thành Phố"
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
        jScrollPane1.setViewportView(tblChiNhanh);

        jSplitPane1.setRightComponent(jScrollPane1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // (Hàm btnLoadCNActionPerformed giữ nguyên)
    private void btnLoadCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadCNActionPerformed
        String endpoint = "/CHINHANH/Index";
        txtLogOutput.setText("Đang tải dữ liệu Chi Nhánh từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.fetchAllSitesData(ipFile, tblChiNhanh, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadCNActionPerformed
    private void btnAddCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCNActionPerformed
        String endpoint = "/CHINHANH/Add";
        
        Map<String, String> params = new HashMap<>();
        // Không gửi maCN (SP sẽ tự tạo)
        params.put("tenCN", txtTenCN.getText());
        params.put("thanhpho", txtThanhPho.getText());

        if (params.get("tenCN").isEmpty() || params.get("thanhpho").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên CN và Thành Phố.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Chi Nhánh đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            // Gọi API (API sẽ gọi sp_TaoChiNhanhTuDong)
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadCNActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnAddCNActionPerformed
    private void btnUpdateCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCNActionPerformed
        String endpoint = "/CHINHANH/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("maCN", txtMaCN.getText());
        params.put("tenCN", txtTenCN.getText());
        params.put("thanhpho", txtThanhPho.getText());

        if (params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Chi Nhánh (Mã CN) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Chi Nhánh đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadCNActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> SỬA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnUpdateCNActionPerformed
    private void btnDeleteCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCNActionPerformed
        String endpoint = "/CHINHANH/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("maCN", txtMaCN.getText());

        if (params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Chi Nhánh (Mã CN) để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc muốn xóa Chi Nhánh: " + params.get("maCN") + "?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh XÓA Chi Nhánh đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);

            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadCNActionPerformed(null);
            } else {
                txtLogOutput.append("\n==> XÓA THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnDeleteCNActionPerformed
    private void setAllButtonsEnabled(boolean enabled) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            btnLoadCN.setEnabled(enabled);
            btnAddCN.setEnabled(enabled);
            btnUpdateCN.setEnabled(enabled);
            btnDeleteCN.setEnabled(enabled);
        } else {
            javax.swing.SwingUtilities.invokeLater(() -> {
                btnLoadCN.setEnabled(enabled);
                btnAddCN.setEnabled(enabled);
                btnUpdateCN.setEnabled(enabled);
                btnDeleteCN.setEnabled(enabled);
            });
        }
    }
    private javax.swing.JButton btnAddCN;
    private javax.swing.JButton btnDeleteCN;
    private javax.swing.JButton btnLoadCN;
    private javax.swing.JButton btnUpdateCN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTable tblChiNhanh;
    private javax.swing.JTextField txtMaCN;
    private javax.swing.JTextField txtTenCN;
    private javax.swing.JTextField txtThanhPho;
}