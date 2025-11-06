package csdlpt;

// Import
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// Thêm các import cho giao diện mới
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;

/**
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HỢP ĐỒNG
 * @author duc18
 */
public class HopDong extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC ===
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    /**
     * Creates new form HopDongPanel
     */
    public HopDong() {
        initComponents(); 
        addTableListeners(); 
    }

    /**
     * Hàm QUAN TRỌNG: MainForm sẽ gọi hàm này
     */
    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;
      //  btnLoadHDActionPerformed(null);
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

    /**
     * HÀM GIAO DIỆN (Đã Viết Lại)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // --- KHAI BÁO BIẾN GIAO DIỆN ---
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

        // --- CẤU HÌNH PANEL CHÍNH (HopDong.java) ---
        setLayout(new java.awt.BorderLayout());

        // --- CẤU HÌNH PANEL BÊN PHẢI (Bảng) ---
        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HĐ", "Ngày Ký", "Mã KH", "Số Điện Kế", "KW Định Mức", "Đơn Giá KW"
            }
        ));
        jScrollPane1.setViewportView(tblHopDong);
        
        // --- CẤU HÌNH PANEL BÊN TRÁI (Form Nhập liệu) ---
        panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin Hợp Đồng"));
        
        jLabel1.setText("Số HĐ:");
        jLabel2.setText("Ngày Ký (yyyy-mm-dd):");
        jLabel3.setText("Mã KH:");
        jLabel4.setText("Số Điện Kế:");
        jLabel5.setText("KW Định Mức:");
        jLabel6.setText("Đơn Giá KW:");

        // Cấu hình panel chứa các nút
        panelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));

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

        // --- Sắp xếp layout cho panelForm (Dùng GroupLayout) ---
        GroupLayout panelFormLayout = new GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setAutoCreateGaps(true);
        panelFormLayout.setAutoCreateContainerGaps(true);

        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoHD)
                    .addComponent(txtNgayKy)
                    .addComponent(txtMaKH_HD)
                    .addComponent(txtSoDienKe)
                    .addComponent(txtKWDinhMuc)
                    .addComponent(txtDonGiaKW)))
            .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelFormLayout.setVerticalGroup(
            panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSoHD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNgayKy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaKH_HD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoDienKe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKWDinhMuc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDonGiaKW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // --- CẤU HÌNH THANH CHIA (JSplitPane) ---
        jSplitPane1.setLeftComponent(panelForm);
        jSplitPane1.setRightComponent(jScrollPane1);
        jSplitPane1.setDividerLocation(380); // Cần rộng hơn
        jSplitPane1.setOneTouchExpandable(true);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
        
    }// </editor-fold>//GEN-END:initComponents

    // === CÁC HÀM SỰ KIỆN (Đã Sửa) ===
    
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
    private javax.swing.JPanel panelForm; // Đã đổi tên
    private javax.swing.JPanel panelButtons; // Panel mới
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1; // Thanh chia
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTextField txtDonGiaKW;
    private javax.swing.JTextField txtKWDinhMuc;
    private javax.swing.JTextField txtMaKH_HD;
    private javax.swing.JTextField txtNgayKy;
    private javax.swing.JTextField txtSoDienKe;
    private javax.swing.JTextField txtSoHD;
    // End of variables declaration//GEN-END:variables
}