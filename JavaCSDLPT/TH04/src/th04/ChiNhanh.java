package th04;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class ChiNhanh extends javax.swing.JFrame {

    private DefaultTableModel tblmodel = null;

    public ChiNhanh() {
        initComponents();
        tblResult.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblResult.getSelectedRow() != -1) {
                int selectedRow = tblResult.getSelectedRow();
                String maCN = tblResult.getValueAt(selectedRow, 0).toString().trim();
                String tenCN = tblResult.getValueAt(selectedRow, 1).toString().trim();
                String diaChi = tblResult.getValueAt(selectedRow, 2).toString().trim();

                txtMaCN.setText(maCN);
                txtTenCN.setText(tenCN);
                txtDiaChi.setText(diaChi);
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
        txtMaCN = new javax.swing.JTextField();
        txtTenCN = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ CHI NHÁNH");

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã CN", "Tên CN", "Địa Chỉ"
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

        jLabel1.setText("Mã Chi Nhánh");

        jLabel2.setText("Tên Chi Nhánh");

        jLabel3.setText("Địa Chỉ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("QUẢN LÝ CHI NHÁNH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaCN, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtTenCN)
                                    .addComponent(txtDiaChi))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnCancel)
                            .addComponent(btnSearch))
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
            File f = new File("D:/IP_ChiNhanh.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("maCN", txtMaCN.getText());
            params.put("tenCN", txtTenCN.getText());
            params.put("diaChi", txtDiaChi.getText());
            db.postToFile(f, txtError, params, "CHINHANH/Add");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi thêm chi nhánh: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File f = new File("D:/IP_ChiNhanh.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("maCN", txtMaCN.getText());
            params.put("tenCN", txtTenCN.getText());
            params.put("diaChi", txtDiaChi.getText());
            db.postToFile(f, txtError, params, "CHINHANH/Update");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi cập nhật chi nhánh: " + e.getMessage());
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            File f = new File("D:/IP_ChiNhanh.txt");
            DataModel db = new DataModel();

            Map<String, String> params = new HashMap<>();
            params.put("maCN", txtMaCN.getText());
            db.postToFile(f, txtError, params, "CHINHANH/Delete");
            loadData();
        } catch (Exception e) {
            txtError.append("\nLỗi xóa chi nhánh: " + e.getMessage());
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear form
        txtMaCN.setText("");
        txtTenCN.setText("");
        txtDiaChi.setText("");
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
        String tenCot[] = {"Mã CN", "Tên CN", "Địa Chỉ"};
        tblmodel = new DefaultTableModel(tenCot, 0);
        tblResult.setModel(tblmodel);
        
        try {
            db.getDataChiNhanh(tblmodel, tblResult, txtError);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextArea txtError;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaCN;
    private javax.swing.JTextField txtTenCN;
}