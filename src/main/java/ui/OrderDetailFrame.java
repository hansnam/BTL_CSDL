
package ui;

import dao.OrderModify;
import java.util.List;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import utils.CustomizeHeaderTable;

public class OrderDetailFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrderDetailFrame.class.getName());

    String orderID;
    DefaultTableModel tableModel;
    
    public OrderDetailFrame(String orderID) {
        initComponents();
        setLocationRelativeTo(null);
        CustomizeHeaderTable.customizeTableHeader(detailTable);
        
        this.orderID = orderID;
        tableModel = (DefaultTableModel) detailTable.getModel();
        showData(orderID);
    }

    private void showData(String orderId) {
        tableModel.setRowCount(0);
        
        List<Object[]> orderDetailList = OrderModify.getOrderDetailForTable(orderId);
        if(orderDetailList.isEmpty()) {
            logger.log(Level.WARNING, "Kh\u00f4ng t\u00ecm th\u1ea5y chi ti\u1ebft ho\u00e1 \u0111\u01a1n: {0}", orderId);
           
        }
        else {
            for (Object[] row : orderDetailList) {
                tableModel.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin chi tiết hoá đơn");

        detailTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        detailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        detailTable.setRowHeight(22);
        jScrollPane1.setViewportView(detailTable);
        if (detailTable.getColumnModel().getColumnCount() > 0) {
            detailTable.getColumnModel().getColumn(0).setMinWidth(40);
            detailTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable detailTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
