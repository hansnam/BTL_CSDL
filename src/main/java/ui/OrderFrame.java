
package ui;

import dao.CustomerModify;
import javax.swing.table.DefaultTableModel;
import dao.OrderModify;
import java.util.List;
import javax.swing.JOptionPane;
import utils.invoiceLayout;

public class OrderFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrderFrame.class.getName());
    
    DefaultTableModel tableModel;

    public OrderFrame() {
        initComponents();
        setLocationRelativeTo(null);
        
        tableModel = (DefaultTableModel) orderTable.getModel();
        loadAllOrders();
        
    }
    
    private void loadAllOrders() {
        
        tableModel.setRowCount(0);

        List<Object[]> orderList = OrderModify.getAllOrdersForTable();

        if (orderList.isEmpty()) {
            logger.warning("Không tìm thấy hóa đơn nào trong CSDL.");
        }
        else {
            for (Object[] row : orderList) {
                
                tableModel.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        issueBtn = new javax.swing.JButton();
        detailBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ ĐƠN HÀNG");

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã đơn hàng", "Mã khách hàng", "Số loại sản phẩm", "Tổng tiền", "Ngày đặt hàng", "Trạng thái đơn hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(0).setResizable(false);
            orderTable.getColumnModel().getColumn(1).setResizable(false);
            orderTable.getColumnModel().getColumn(2).setResizable(false);
            orderTable.getColumnModel().getColumn(3).setResizable(false);
            orderTable.getColumnModel().getColumn(4).setResizable(false);
            orderTable.getColumnModel().getColumn(5).setResizable(false);
            orderTable.getColumnModel().getColumn(6).setResizable(false);
        }

        issueBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        issueBtn.setText("Xuất hoá đơn");
        issueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtnActionPerformed(evt);
            }
        });

        detailBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        detailBtn.setText("Chi tiết đơn hàng");
        detailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailBtnActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtn.setText("Tìm kiếm đơn hàng");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(678, 678, 678)
                        .addComponent(searchBtn)
                        .addGap(63, 63, 63)
                        .addComponent(detailBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(issueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(issueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void detailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailBtnActionPerformed
        
        int selectedRow = orderTable.getSelectedRow();
        
        // Kiểm tra xem người dùng đã chọn hàng nào chưa
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn một hoá đơn để xem chi tiết.", 
                    "Chưa chọn hoá đơn", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Lấy Mã đơn hàng (OrderID) từ cột 1 (vì cột 0 là STT)
        String orderId = tableModel.getValueAt(selectedRow, 1).toString();
        OrderDetailFrame detailFrame = new OrderDetailFrame(orderId);
        detailFrame.setVisible(true);
        // Đặt vị trí của cửa sổ chi tiết ở giữa cửa sổ hiện tại (OrderFrame)
        detailFrame.setLocationRelativeTo(this);
    }//GEN-LAST:event_detailBtnActionPerformed

    private void issueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueBtnActionPerformed

        int selectedRow = orderTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn một hoá đơn để xuất file.",
                    "Chưa chọn hoá đơn",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            //Lấy thông tin từ hàng được chọn trong bảng
            String orderId = tableModel.getValueAt(selectedRow, 1).toString();
            String customerId = tableModel.getValueAt(selectedRow, 2).toString();
            String orderDate = tableModel.getValueAt(selectedRow, 5).toString();

            //Lấy thông tin khách hàng (Name/CompanyName và Address)
            
            String customerName = CustomerModify.getCustomerNameById(customerId); 
            String address = CustomerModify.getCustomerAddressById(customerId);      

            //Lấy danh sách chi tiết hoá đơn từ cơ sở dữ liệu
            List<Object[]> details = OrderModify.getOrderDetailForTable(orderId);
            if (details == null || details.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Không có chi tiết đơn hàng để xuất hoá đơn.",
                        "Thiếu dữ liệu",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

          
            invoiceLayout.exportPDF(orderId, customerId, customerName, address, orderDate, details);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi xuất hoá đơn PDF: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_issueBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        // 1. Hiển thị hộp thoại nhập liệu và lấy giá trị nhập vào
        String searchText = JOptionPane.showInputDialog(this,
                "Vui lòng nhập mã đơn hàng cần tìm (hoặc * để hiển thị tất cả)", // Nội dung thông báo
                "Tìm kiếm đơn hàng", // Tiêu đề hộp thoại
                JOptionPane.QUESTION_MESSAGE);

        // Kiểm tra nếu người dùng nhấn Cancel (hủy) hoặc đóng hộp thoại (searchText sẽ là null)
        if (searchText == null) {
            return;
        }

        // Loại bỏ khoảng trắng (trim) sau khi người dùng nhập
        searchText = searchText.trim();

        // 2. Xử lý logic tìm kiếm
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Mã đơn hàng không được để trống.",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Nếu nhập dấu * thì hiển thị lại toàn bộ danh sách
        if (searchText.equals("*")) {
            loadAllOrders();
            return;
        }

        // Xóa dữ liệu cũ trong bảng
        tableModel.setRowCount(0);

        // Gọi phương thức tìm kiếm trong OrderModify
        List<Object[]> searchResult = OrderModify.findOrderById(searchText);

        if (searchResult == null || searchResult.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Không tìm thấy đơn hàng với mã: " + searchText,
                    "Kết quả tìm kiếm",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Object[] row : searchResult) {
                tableModel.addRow(row);
            }
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton detailBtn;
    private javax.swing.JButton issueBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}
