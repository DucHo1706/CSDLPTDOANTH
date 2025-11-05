package th04;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class HopDong extends javax.swing.JFrame {

    private DefaultTableModel tblmodel = null;

    public HopDong() {
        initComponents();
        tblResult.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblResult.getSelectedRow() != -1) {
                int selectedRow = tblResult.getSelectedRow();
                String soHD = tblResult.getValueAt(selectedRow, 0).toString().trim();
                String ngayKy = tblResult.getValueAt(selectedRow, 1).toString().trim();
                String maKH = tblResult.getValueAt(selectedRow, 2).toString().trim();
                String soDienKe = tblResult.getValueAt(selectedRow, 3).toString().trim();
                String kwDinhMuc = tblResult.getValueAt(selectedRow, 4).toString().trim();
                String dongiaKW = tblResult.getValueAt(selectedRow, 5).toString().trim();

                txtSoHD.setText(soHD);
                txtNgayKy.setText(ngayKy);
                txtMaKH.setText(maKH);
                txtSoDienKe.setText(soDienKe);
                txtKwDinhMuc.setText(kwDinhMuc);
                txtDonGia.setText(dongiaKW);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtError = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        txtNgayKy = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtSoDienKe = new javax.swing.JTextField();
        txtKwDinhMuc = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ HỢP ĐỒNG");

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Số HD", "Ngày Ký", "Mã KH", "Số Điện Kế", "KW Định Mức", "Đơn Giá KW"
            }
        ));
        jScrollPane1.setViewportView(tblResult);

        txtError.setColumns(20);
        txtError.setRows(5);
        jScrollPane2.setViewportView(txtError);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel1.setText("Số Hợp Đồng");

        jLabel2.setText("Ngày Ký");

        jLabel3.setText("Mã Khách Hàng");

        jLabel4.setText("Số Điện Kế");

        jLabel5.setText("KW Định Mức");

        jLabel6.setText("Đơn Giá KW");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setText("QUẢN LÝ HỢP ĐỒNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoHD)
                                    .addComponent(txtNgayKy)
                                    .addComponent(txtMaKH)
                                    .addComponent(txtSoDienKe)
                                    .addComponent(txtKwDinhMuc)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSearch)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEdit))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSave)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancel))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNgayKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSoDienKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtKwDinhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnCancel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File f = new File("D:/IP_HopDong.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHD", txtSoHD.getText());
            params.put("ngayKy", txtNgayKy.getText());
            params.put("maKH", txtMaKH.getText());
            params.put("soDienKe", txtSoDienKe.getText());
            params.put("kwDinhMuc", txtKwDinhMuc.getText());
            params.put("dongiaKW", txtDonGia.getText());
            db.postToFile(f, txtError, params, "HOPDONG/Add");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi thêm hợp đồng: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File f = new File("D:/IP_HopDong.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHD", txtSoHD.getText());
            params.put("ngayKy", txtNgayKy.getText());
            params.put("maKH", txtMaKH.getText());
            params.put("soDienKe", txtSoDienKe.getText());
            params.put("kwDinhMuc", txtKwDinhMuc.getText());
            params.put("dongiaKW", txtDonGia.getText());
            db.postToFile(f, txtError, params, "HOPDONG/Update");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi cập nhật hợp đồng: " + e.getMessage());
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File f = new File("D:/IP_HopDong.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("soHD", txtSoHD.getText());
            db.postToFile(f, txtError, params, "HOPDONG/Delete");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi xóa hợp đồng: " + e.getMessage());
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear form
        txtSoHD.setText("");
        txtNgayKy.setText("");
        txtMaKH.setText("");
        txtSoDienKe.setText("");
        txtKwDinhMuc.setText("");
        txtDonGia.setText("");
        txtError.setText("");
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        loadData();
    }

    private void loadData() {
        DataModel db = new DataModel();
        String tenCot[] = {"Số HD", "Ngày Ký", "Mã KH", "Số Điện Kế", "KW Định Mức", "Đơn Giá KW"};
        tblmodel = new DefaultTableModel(tenCot, 0);
        tblResult.setModel(tblmodel);
        
        try {
            db.getDataHopDong(tblmodel, tblResult, txtError);
        } catch (Exception e) {
            txtError.append("\nLỗi tải dữ liệu: " + e.getMessage());
        }
    }

    // Variables declaration
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextArea txtError;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtKwDinhMuc;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgayKy;
    private javax.swing.JTextField txtSoDienKe;
    private javax.swing.JTextField txtSoHD;
}