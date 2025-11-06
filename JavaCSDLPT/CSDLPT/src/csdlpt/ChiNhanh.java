package csdlpt;

// Import các thư viện cần thiết
import java.awt.BorderLayout;
import java.awt.FlowLayout; // Thêm import
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
 * Giao diện (JPanel) CHỈ DÀNH CHO TAB CHI NHÁNH
 * (Đã cập nhật giao diện Master-Detail Trái/Phải)
 * @author duc18
 */
public class ChiNhanh extends javax.swing.JPanel {

    // === 1. BIẾN LOGIC ===
    private DataModel dataModel;
    private File ipFile;
    private JTextArea txtLogOutput;

    /**
     * Creates new form ChiNhanhPanel
     */
    public ChiNhanh() {
        initComponents(); // Hàm này tôi đã viết thủ công ở dưới
        addTableListeners();
    }

    /**
     * Hàm QUAN TRỌNG: MainForm sẽ gọi hàm này
     */
    public void init(DataModel model, File file, JTextArea log) {
        this.dataModel = model;
        this.ipFile = file;
        this.txtLogOutput = log;

        // Tải dữ liệu ngay khi khởi tạo
        //  btnLoadCNActionPerformed(null);
    }

    /**
     * Hàm tự động điền dữ liệu khi click vào bảng
     */
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

   /**
     * HÀM GIAO DIỆN (ĐÃ VIẾT LẠI THEO BỐ CỤC MASTER-DETAIL)
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // --- KHAI BÁO BIẾN GIAO DIỆN ---
        jSplitPane1 = new javax.swing.JSplitPane();
        panelForm = new javax.swing.JPanel(); // Panel bên trái
        panelButtons = new javax.swing.JPanel(); // Panel chứa các nút
        
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

        // --- CẤU HÌNH PANEL CHÍNH (ChiNhanh.java) ---
        setLayout(new java.awt.BorderLayout());

        // --- CẤU HÌNH PANEL BÊN PHẢI (Bảng) ---
        tblChiNhanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CN", "Tên CN", "Thành Phố"
            }
        ));
        jScrollPane1.setViewportView(tblChiNhanh);
        
        // --- CẤU HÌNH PANEL BÊN TRÁI (Form Nhập liệu) ---
        panelForm.setBorder(BorderFactory.createTitledBorder("Thông tin Chi Nhánh"));
        
        jLabel1.setText("Mã Chi Nhánh:");
        jLabel2.setText("Tên Chi Nhánh:");
        jLabel3.setText("Thành Phố:");

        // Cấu hình panel chứa các nút (dùng FlowLayout để các nút nằm ngang)
        panelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));

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

        // --- Sắp xếp layout cho panelForm (Dùng GroupLayout) ---
        GroupLayout panelFormLayout = new GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setAutoCreateGaps(true);
        panelFormLayout.setAutoCreateContainerGaps(true);

        // -- Căn chỉnh chiều ngang (Horizontal) --
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaCN)
                    .addComponent(txtTenCN)
                    .addComponent(txtThanhPho)))
            .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // -- Căn chỉnh chiều dọc (Vertical) --
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createSequentialGroup()
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaCN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenCN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtThanhPho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18) // Thêm khoảng cách
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // --- CẤU HÌNH THANH CHIA (JSplitPane) ---
        jSplitPane1.setLeftComponent(panelForm);    // Đặt form bên trái
        jSplitPane1.setRightComponent(jScrollPane1); // Đặt bảng bên phải
        jSplitPane1.setDividerLocation(350);         // Đặt vị trí thanh chia ban đầu
        jSplitPane1.setOneTouchExpandable(true);     // Cho phép thu gọn/mở rộng nhanh

        // 4. Thêm JSplitPane vào panel chính (ChiNhanh.java)
        add(jSplitPane1, java.awt.BorderLayout.CENTER);
        
    }// </editor-fold>//GEN-END:initComponents

    // === CÁC HÀM SỰ KIỆN (ĐÃ SỬA LẠI) ===

    private void btnLoadCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadCNActionPerformed
        String endpoint = "/CHINHANH/Index";
        txtLogOutput.setText("Đang tải dữ liệu Chi Nhánh từ tất cả các site...\n");
        
        // --- CHẠY TRÊN LUỒNG MỚI ---
        new Thread(() -> {
            setAllButtonsEnabled(false);
            dataModel.getDataFromAllSites(ipFile, tblChiNhanh, txtLogOutput, endpoint);
            // Bật lại nút trên luồng giao diện
            javax.swing.SwingUtilities.invokeLater(() -> {
                setAllButtonsEnabled(true);
            });
        }).start();
    }//GEN-LAST:event_btnLoadCNActionPerformed

    private void btnAddCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCNActionPerformed
        String endpoint = "/CHINHANH/Add"; //
        
        Map<String, String> params = new HashMap<>();
        params.put("maCN", txtMaCN.getText());
        params.put("tenCN", txtTenCN.getText());
        params.put("thanhpho", txtThanhPho.getText()); //

        if (params.get("maCN").isEmpty() || params.get("tenCN").isEmpty() || params.get("thanhpho").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh THÊM Chi Nhánh đến MÁY CHÍNH...\n");
        
        // --- CHẠY TRÊN LUỒNG MỚI VÀ KIỂM TRA KẾT QUẢ ---
        new Thread(() -> {
            setAllButtonsEnabled(false);
            boolean isSuccess = dataModel.postToMaster(txtLogOutput, params, endpoint);
            
            if(isSuccess) {
                txtLogOutput.append("\nĐang tải lại dữ liệu (chờ Replication)...");
                try { Thread.sleep(2000); } catch (Exception e) {}
                btnLoadCNActionPerformed(null); // Tự động bật lại nút sau khi tải xong
            } else {
                txtLogOutput.append("\n==> THÊM THẤT BẠI. Dữ liệu sẽ không được tải lại.");
                // Bật lại nút nếu thất bại
                javax.swing.SwingUtilities.invokeLater(() -> {
                    setAllButtonsEnabled(true);
                });
            }
        }).start();
    }//GEN-LAST:event_btnAddCNActionPerformed

    private void btnUpdateCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCNActionPerformed
        String endpoint = "/CHINHANH/Update"; //
        
        Map<String, String> params = new HashMap<>();
        params.put("maCN", txtMaCN.getText());
        params.put("tenCN", txtTenCN.getText());
        params.put("thanhpho", txtThanhPho.getText()); //

        if (params.get("maCN").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn một Chi Nhánh (Mã CN) để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtLogOutput.setText("Đang gửi lệnh SỬA Chi Nhánh đến MÁY CHÍNH...\n");
        
        // --- CHẠY TRÊN LUỒNG MỚI VÀ KIỂM TRA KẾT QUẢ ---
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
        String endpoint = "/CHINHANH/Delete"; //
        
        Map<String, String> params = new HashMap<>();
        params.put("maCN", txtMaCN.getText()); //

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
        
        // --- CHẠY TRÊN LUỒNG MỚI VÀ KIỂM TRA KẾT QUẢ ---
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

    /**
     * Hàm tiện ích để bật/tắt tất cả các nút (chạy trên luồng giao diện)
     */
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCN;
    private javax.swing.JButton btnDeleteCN;
    private javax.swing.JButton btnLoadCN;
    private javax.swing.JButton btnUpdateCN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panelForm; // Đã đổi tên
    private javax.swing.JPanel panelButtons; // Panel mới
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1; // Thanh chia
    private javax.swing.JTable tblChiNhanh;
    private javax.swing.JTextField txtMaCN;
    private javax.swing.JTextField txtTenCN;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
}