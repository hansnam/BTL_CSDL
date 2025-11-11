
package ui;

import dao.OrderModify;
import dao.ProductModify;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Product;

public class MainFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    

    DefaultTableModel productModel;
    DefaultTableModel cartModel;
    List<Product> productList = new ArrayList<>();

    public MainFrame() {
        initComponents();
        setLocationRelativeTo(null);
        
        loadProducts();
    }

    private void loadProducts() {
        productModel = (DefaultTableModel) productTable.getModel();
        cartModel = (DefaultTableModel) cartTable.getModel();
        productModel.setRowCount(0);

        List<Product> list = ProductModify.getProductList(null);
        int i = 1;
        for (Product p : list) {
            productModel.addRow(new Object[]{
                i++,
                p.getName(),
                p.getPrice(),
                p.getDescriptions()
            });
        }
        
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // int click
                    int row = productTable.getSelectedRow();
                    if (row >= 0) {
                        String productName = productModel.getValueAt(row, 1).toString();
                        int price = Integer.parseInt(productModel.getValueAt(row, 2).toString());

                        boolean found = false;
                        for (int i = 0; i < cartModel.getRowCount(); i++) {
                            String existingName = cartModel.getValueAt(i, 1).toString();
                            if (existingName.equals(productName)) {
                                int qty = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                                qty++;
                                cartModel.setValueAt(qty, i, 2);
                                cartModel.setValueAt(price * qty, i, 3);
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            cartModel.addRow(new Object[]{
                                cartModel.getRowCount() + 1,
                                productName,
                                1,
                                price
                            });
                        }
                    }
                }
            }
        });

    }

    private void addToCart(int row) {
        String productName = productModel.getValueAt(row, 1).toString();
        int price = Integer.parseInt(productModel.getValueAt(row, 2).toString());

        boolean found = false;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            String cartName = cartModel.getValueAt(i, 1).toString();
            if (cartName.equals(productName)) {
                int quantity = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                quantity++;
                int total = quantity * price;
                cartModel.setValueAt(quantity, i, 2);
                cartModel.setValueAt(total, i, 3);
                found = true;
                break;
            }
        }

        if (!found) {
            int stt = cartModel.getRowCount() + 1;
            cartModel.addRow(new Object[]{stt, productName, 1, price});
        }
    }
    
    private int calculateTotalAmount() {
        int total = 0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            // Lấy tổng tiền từ cột 3 (Thành tiền)
            try {
                total += Integer.parseInt(cartModel.getValueAt(i, 3).toString());
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "L\u1ed7i \u0111\u1ecbnh d\u1ea1ng s\u1ed1 \u1edf c\u1ed9t th\u00e0nh ti\u1ec1n, h\u00e0ng: {0}", i);
            }
        }
        return total;
    }
    
    
    private void showData(String keyword) {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        List<Product> list = ProductModify.getProductList(keyword); // Lấy danh sách từ DB
        int i = 1;
        for (Product p : list) {
            model.addRow(new Object[]{
                i++,
                p.getName(),
                p.getPrice(),
                p.getDescriptions()
            });
        }
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        manageMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        receiptMenu = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ BÁN HÀNG");
        setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Giá tiền", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(3).setResizable(false);
        }

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cartTable);
        if (cartTable.getColumnModel().getColumnCount() > 0) {
            cartTable.getColumnModel().getColumn(0).setResizable(false);
            cartTable.getColumnModel().getColumn(1).setResizable(false);
            cartTable.getColumnModel().getColumn(2).setResizable(false);
            cartTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("GIỎ HÀNG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("SẢN PHẨM");

        saveBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveBtn.setText("Lưu đơn hàng");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        delBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delBtn.setText("Xoá giỏ hàng");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        removeBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        removeBtn.setText("Xoá sản phẩm");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        searchBtn.setText("Tìm kiếm sản phẩm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        manageMenu.setText("Quản lý");

        jMenuItem1.setText("Nhân sự");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        manageMenu.add(jMenuItem1);

        jMenuItem2.setText("Khách hàng");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        manageMenu.add(jMenuItem2);

        jMenuItem3.setText("Sản phẩm");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        manageMenu.add(jMenuItem3);

        jMenuBar1.add(manageMenu);

        receiptMenu.setText("Đơn hàng");

        jMenuItem4.setText("Xem đơn hàng");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        receiptMenu.add(jMenuItem4);

        jMenuBar1.add(receiptMenu);

        reportMenu.setText("Báo cáo");

        jMenuItem5.setText("Xem báo cáo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        reportMenu.add(jMenuItem5);

        jMenuBar1.add(reportMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeBtn)
                            .addComponent(delBtn)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new EmployeeFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        
        int quantityType = cartModel.getRowCount();

        if (quantityType == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống, không thể lưu!");
            return;
        }

        String customerId = JOptionPane.showInputDialog(this,
                "Vui lòng nhập mã khách hàng :",
                "Xác nhận đơn hàng (1/2)",
                JOptionPane.PLAIN_MESSAGE);

        if (customerId == null || customerId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng là bắt buộc! Đã huỷ đơn hàng.",
                    "Huỷ bỏ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String staffId = JOptionPane.showInputDialog(this,
                "Vui lòng nhập Mã Nhân viên: ",
                "Xác nhận đơn hàng (2/2)",
                JOptionPane.PLAIN_MESSAGE);

        if (staffId == null || staffId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên là bắt buộc! Đã huỷ đơn hàng.",
                    "Huỷ bỏ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String managerId = JOptionPane.showInputDialog(this,
                "Vui lòng nhập Mã quản lý: ",
                "Xác nhận đơn hàng (2/2)",
                JOptionPane.PLAIN_MESSAGE);

        if (managerId == null || managerId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã quản lý là bắt buộc! Đã huỷ đơn hàng.",
                    "Huỷ bỏ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        

        try {
            String orderId = JOptionPane.showInputDialog(this,
                    "Vui lòng nhập mã đơn hàng:",
                    "Nhập mã đơn hàng",
                    JOptionPane.PLAIN_MESSAGE);

            if (orderId == null || orderId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã đơn hàng là bắt buộc! Đã huỷ lưu đơn hàng.",
                        "Huỷ bỏ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra mã đơn hàng có bị trùng trong CSDL không
            if (OrderModify.isOrderIdExists(orderId)) {
                JOptionPane.showMessageDialog(this, "Mã đơn hàng đã tồn tại! Vui lòng nhập mã khác.",
                        "Trùng mã", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int totalAmount = (int) calculateTotalAmount();
            LocalDateTime now = LocalDateTime.now();
            Timestamp orderDate = Timestamp.valueOf(now);
            String status = "Đã hoàn thành";

            boolean orderSaved = OrderModify.insertOrder(orderId, customerId, (int) totalAmount,
                    orderDate, status, managerId, quantityType);

            if (!orderSaved) {
                JOptionPane.showMessageDialog(this, "Lỗi CSDL: Không thể lưu đơn hàng chính! \n(Kiểm tra Log CSDL)",
                        "Lỗi CSDL", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean allDetailsSaved = true;
            for (int i = 0; i < quantityType; i++) { 
                String productName = cartModel.getValueAt(i, 1).toString();
                int quantity = Integer.parseInt(cartModel.getValueAt(i, 2).toString());
                String productId = ProductModify.getProductIdByName(productName);

                if (productId == null) {
                    logger.log(Level.WARNING, "Kh\u00f4ng t\u00ecm th\u1ea5y ProductID cho t\u00ean: {0}. B\u1ecf qua.", productName);
                    allDetailsSaved = false;
                    continue;
                }

                if (!OrderModify.insertOrderDetail(orderId, productId, quantity)) {
                    allDetailsSaved = false;
                    logger.severe(() -> "Lỗi CSDL khi lưu chi tiết: " + orderId + " - " + productId);
                }
            }

            if (allDetailsSaved) {
                JOptionPane.showMessageDialog(this, "Đã lưu đơn hàng " + orderId + " (NV: " + managerId + ") vào CSDL thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Đơn hàng " + orderId + " đã được lưu,"
                        + "NHƯNG một số chi tiết sản phẩm có thể đã bị lỗi.",
                        "Lỗi một phần", JOptionPane.WARNING_MESSAGE);
            }

            cartModel.setRowCount(0); // Xóa giỏ hàng

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Lỗi nghiêm trọng khi lưu đơn hàng", e);
            JOptionPane.showMessageDialog(this, "Lỗi nghiêm trọng khi lưu đơn hàng: " + e.getMessage());
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        javax.swing.table.DefaultTableModel cartModel = (javax.swing.table.DefaultTableModel) cartTable.getModel();
        cartModel.setRowCount(0);
        javax.swing.JOptionPane.showMessageDialog(this, "Đơn hàng đã được xóa!");
    }//GEN-LAST:event_delBtnActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new ReportFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new OrderFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
                       
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new CustomerFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new ProductFrame().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed

        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
            return;
        }

        javax.swing.table.DefaultTableModel cartModel = (javax.swing.table.DefaultTableModel) cartTable.getModel();
        cartModel.removeRow(selectedRow);

        // Cập nhật lại STT sau khi xóa
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            cartModel.setValueAt(i + 1, i, 0);
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        String s = JOptionPane.showInputDialog("Nhập tên sản phẩm cần tìm kiếm (hoặc nhập * để hiện tất cả): ");

        if (s == null) {
            return;  // người dùng bấm Cancel
        }
        if (s.trim().equals("*") || s.trim().isEmpty()) {
            showData(null);
        } else {
            s = "%" + s.trim() + "%";
            showData(s);
        }

    }//GEN-LAST:event_searchBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cartTable;
    private javax.swing.JButton delBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu manageMenu;
    private javax.swing.JTable productTable;
    private javax.swing.JMenu receiptMenu;
    private javax.swing.JButton removeBtn;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}
