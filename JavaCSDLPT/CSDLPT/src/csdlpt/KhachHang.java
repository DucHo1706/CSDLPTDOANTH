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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB KHÁCH HÀNG
 * @author duc18
 */
public class KhachHang extends javax.swing.JPanel {

    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    public KhachHang() {
        initComponents();
        addTableListeners(); 
    }

    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;

      //  btnLoadKHActionPerformed(null);
    }
    private void addTableListeners() {
        tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && tblKhachHang.getSelectedRow() != -1) {
                    int row = tblKhachHang.getSelectedRow();
                    txtMaKH.setText(tblKhachHang.getValueAt(row, 0).toString());
                    txtTenKH.setText(tblKhachHang.getValueAt(row, 1).toString());
                    txtMaCN_KH.setText(tblKhachHang.getValueAt(row, 2).toString());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // --- KHAI BÁO BIẾN GIAO DIỆN ---
        jSplitPane1 = new javax.swing.JSplitPane();
        panelForm = new javax.swing.JPanel(); // Panel bên trái
        panelButtons = new javax.swing.JPanel(); // Panel chứa các nút
        
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtMaCN_KH = new javax.swing.JTextField();
        btnLoadKH = new javax.swing.JButton();
        btnAddKH = new javax.swing.JButton();
        btnUpdateKH = new javax.swing.JButton();
        btnDeleteKH = new javax.swing.JButton();

        // --- CẤU HÌNH PANEL CHÍNH (KhachHang.java) ---
        setLayout(new java.awt.BorderLayout());

        // --- CẤU HÌNH PANEL BÊN PHẢI (Bảng) ---
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Mã CN"
            }
        ));
        jScrollPane1.setViewportView(tblKhachHang);
        
        // --- CẤU HÌNH PANEL BÊN TRÁI (Form Nhập liệu) ---
        panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin Khách Hàng"));
        
        jLabel1.setText("Mã Khách Hàng:");
        jLabel2.setText("Tên Khách Hàng:");
        jLabel3.setText("Mã Chi Nhánh:");

        // Cấu hình panel chứa các nút
        panelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));

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
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaKH)
                    .addComponent(txtTenKH)
                    .addComponent(txtMaCN_KH)))
            .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelFormLayout.setVerticalGroup(
            panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaCN_KH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18) 
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // --- CẤU HÌNH THANH CHIA (JSplitPane) ---
        jSplitPane1.setLeftComponent(panelForm);
        jSplitPane1.setRightComponent(jScrollPane1);
        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setOneTouchExpandable(true);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
        
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnLoadKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Index";
        txtLogOutput.setText("Đang tải dữ liệu Khách Hàng từ tất cả các site...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.getDataFromAllSites(ipFile, tblKhachHang, txtLogOutput, endpoint);
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }

    private void btnAddKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Add";
        
        Map<String, String> params = new HashMap<>();
        params.put("maKH", txtMaKH.getText());
        params.put("tenKH", txtTenKH.getText());
        params.put("maCN", txtMaCN_KH.getText());

        if (params.get("maKH").isEmpty() || params.get("tenKH").isEmpty() || params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Khách Hàng đến MÁY CHÍNH...\n");
        
        new Thread(() -> {
            setAllButtonsEnabled(false);
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

    private void btnUpdateKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Update";
        
        Map<String, String> params = new HashMap<>();
        params.put("maKH", txtMaKH.getText());
        params.put("tenKH", txtTenKH.getText());
        params.put("maCN", txtMaCN_KH.getText());

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

    private void btnDeleteKHActionPerformed(java.awt.event.ActionEvent evt) {
        String endpoint = "/KHACHHANG/Delete";
        
        Map<String, String> params = new HashMap<>();
        params.put("maKH", txtMaKH.getText());

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
    
    /**
     * Hàm tiện ích để bật/tắt tất cả các nút (chạy trên luồng giao diện)
     */
    private void setAllButtonsEnabled(boolean enabled) {
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
    private javax.swing.JPanel panelForm; // Đã đổi tên
    private javax.swing.JPanel panelButtons; // Panel mới
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1; // Thanh chia
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtMaCN_KH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}