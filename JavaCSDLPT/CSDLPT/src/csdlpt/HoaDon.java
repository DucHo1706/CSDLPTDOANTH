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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB HÓA ĐƠN
 * @author duc18
 */
public class HoaDon extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC ===
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    /**
     * Creates new form HoaDonPanel
     */
    public HoaDon() {
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
      //  btnLoadHOADONActionPerformed(null);
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

        // --- CẤU HÌNH PANEL CHÍNH (HoaDon.java) ---
        setLayout(new java.awt.BorderLayout());

        // --- CẤU HÌNH PANEL BÊN PHẢI (Bảng) ---
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số HĐN", "Tháng", "Năm", "Số HĐ", "Mã NV", "Số Tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDon);

        // --- CẤU HÌNH PANEL BÊN TRÁI (Form Nhập liệu) ---
        panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin Hóa Đơn"));
        
        jLabel1.setText("Số HĐN (Auto):");
        txtSoHDN.setEditable(false); // Khóa trường tự tăng
        txtSoHDN.setFocusable(false);
        
        jLabel2.setText("Tháng:");
        jLabel3.setText("Năm:");
        jLabel4.setText("Số HĐ (Hợp Đồng):");
        jLabel5.setText("Mã NV (Lập HĐ):");
        jLabel6.setText("Số Tiền:");

        // Cấu hình panel chứa các nút
        panelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));
        
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

        // --- Sắp xếp layout cho panelForm (Dùng GroupLayout) ---
        GroupLayout panelFormLayout = new GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setAutoCreateGaps(true);
        panelFormLayout.setAutoCreateContainerGaps(true);

        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoHDN)
                    .addComponent(txtThang)
                    .addComponent(txtNam)
                    .addComponent(txtSoHD_HOADON)
                    .addComponent(txtMaNV_HOADON)
                    .addComponent(txtSoTien)))
            .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelFormLayout.setVerticalGroup(
            panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSoHDN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtThang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoHD_HOADON, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaNV_HOADON, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoTien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JPanel panelForm; // Đã đổi tên
    private javax.swing.JPanel panelButtons; // Panel mới
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1; // Thanh chia
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaNV_HOADON;
    private javax.swing.JTextField txtNam;
    private javax.swing.JTextField txtSoHDN;
    private javax.swing.JTextField txtSoHD_HOADON;
    private javax.swing.JTextField txtSoTien;
    private javax.swing.JTextField txtThang;
    // End of variables declaration//GEN-END:variables
}