
package ui;

import dao.ProductModify;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Product;
import utils.CustomizeHeaderTable;

public class ProductFrame extends javax.swing.JFrame {
    
    DefaultTableModel tableModel;
    List<Product> datalist = new ArrayList<>();
    int pos = -1;
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProductFrame.class.getName());

    public ProductFrame() {
        initComponents();
        setLocationRelativeTo(null);
        CustomizeHeaderTable.customizeTableHeader(productTable);
        
        tableModel = (DefaultTableModel) productTable.getModel();
        datalist = ProductModify.getProductList(null);
        
        showData();
        
        productTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pos = productTable.getSelectedRow();
                
                idTxt.setText(datalist.get(pos).getID());
                nameTxt.setText(datalist.get(pos).getName());
                priceTxt.setText(String.valueOf(datalist.get(pos).getPrice()));
                desTxt.setText(datalist.get(pos).getDescriptions());

            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
    }
    
    private void showData() {
        tableModel.setRowCount(0);
        for(Product p : datalist) {
            tableModel.addRow(new Object[] {
                tableModel.getRowCount() + 1,
                p.getID(),
                p.getName(),
                p.getPrice(),
                p.getDescriptions()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        idTxt = new javax.swing.JTextField();
        nameTxt = new javax.swing.JTextField();
        priceTxt = new javax.swing.JTextField();
        desTxt = new javax.swing.JTextField();
        saveBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ SẢN PHẨM");

        productTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá thành", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setRowHeight(22);
        jScrollPane1.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setMinWidth(40);
            productTable.getColumnModel().getColumn(0).setMaxWidth(100);
            productTable.getColumnModel().getColumn(1).setMinWidth(100);
            productTable.getColumnModel().getColumn(1).setMaxWidth(150);
            productTable.getColumnModel().getColumn(2).setMinWidth(150);
            productTable.getColumnModel().getColumn(3).setMinWidth(100);
            productTable.getColumnModel().getColumn(3).setMaxWidth(200);
            productTable.getColumnModel().getColumn(4).setMinWidth(200);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã sản phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giá thành");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mô tả");

        idTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        nameTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        priceTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        desTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        saveBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saveBtn.setText("Lưu");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchBtn.setText("Tìm kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        delBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        delBtn.setText("Xoá");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idTxt)
                            .addComponent(nameTxt)
                            .addComponent(priceTxt)
                            .addComponent(desTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(desTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        if (pos == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn sản phẩm cần xoá, vui lòng kiểm tra lại!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xoá sản phẩm này không?");
        if(option == 0) {
            ProductModify.delete(datalist.get(pos).getID());
            datalist.remove(pos);
            pos = -1;
            showData();
        }
        idTxt.setText("");
        nameTxt.setText("");
        priceTxt.setText("");
        desTxt.setText("");        
    }//GEN-LAST:event_delBtnActionPerformed
                               

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String s = JOptionPane.showInputDialog("NHập tên sản phẩm cần tìm kiếm");
        if(!s.isEmpty()) {
            s = "%"+s+"%";
        }
        datalist = ProductModify.getProductList(s);
        showData();
    }//GEN-LAST:event_searchBtnActionPerformed

    private boolean isValidProduct () {
        return !idTxt.getText().isEmpty() &&
                !nameTxt.getText().isEmpty() &&
                !priceTxt.getText().isEmpty() &&
                !desTxt.getText().isEmpty();
    }
    
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (!isValidProduct()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin sản phẩm.");
            return;
        }
        if (pos >= 0) {
            datalist.get(pos).setID(idTxt.getText());
            datalist.get(pos).setName(nameTxt.getText());
            datalist.get(pos).setPrice(Integer.parseInt(priceTxt.getText()));
            datalist.get(pos).setDescriptions(desTxt.getText());
            
            ProductModify.update(datalist.get(pos));
            pos = -1;
        } else {
            Product p = new Product(
                idTxt.getText(),
                nameTxt.getText(),
                Integer.parseInt(priceTxt.getText()),
                desTxt.getText()
            );
            ProductModify.insert(p);
            datalist = ProductModify.getProductList(null);
        }
        showData();
        
        idTxt.setText("");
        nameTxt.setText("");
        priceTxt.setText("");
        desTxt.setText("");
        
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delBtn;
    private javax.swing.JTextField desTxt;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField priceTxt;
    private javax.swing.JTable productTable;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}
