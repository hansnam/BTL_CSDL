package ui;

import dao.OrderModify;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.Order;

public class OrderDFrame extends javax.swing.JFrame {
    DefaultTableModel tableModel;
    List<Order> orderList = new ArrayList<>();
    int selectedRow = -1;

    public OrderDFrame() {
        initComponents();
        loadOrderData();
    }

    private void loadOrderData() {
        tableModel = (DefaultTableModel) orderTable.getModel();
        tableModel.setRowCount(0);

        orderList = OrderModify.getOrderList();
        for (Order o : orderList) {
            tableModel.addRow(new Object[]{
                o.getId(),
                o.getQuantityType(),
                o.getTotalAmount(),
                o.getDate(),
                o.getStatus()
            });
        }
    }

    private void exportInvoice(String orderID) {
        try (ResultSet rs = OrderModify.getOrderDetail(orderID)) {

            // Kiểm tra xem đơn hàng có tồn tại không
            if (rs == null || !rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết đơn hàng với ID: " + orderID);
                return;
            }

            // Tạo file hóa đơn
            File file = new File("hoadon_" + orderID + ".txt");
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("======== HÓA ĐƠN BÁN HÀNG ========");
                writer.println("Mã hóa đơn: " + orderID);

                // Định dạng ngày giờ
                java.time.format.DateTimeFormatter formatter
                        = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                writer.println("Ngày lập: " + java.time.LocalDateTime.now().format(formatter));
                writer.println("----------------------------------");
                writer.printf("%-5s %-25s %-10s %-15s\n", "STT", "Tên sản phẩm", "Số lượng", "Thành tiền");

                int i = 1;
                double total = 0;
                while (rs.next()) {
                    String name = rs.getString("ProductName");
                    int qty = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    double sub = rs.getDouble("SubTotal");
                    total += sub;

                    writer.printf("%-5d %-25s %-10d %-15.0f\n", i++, name, qty, sub);
                }

                writer.println("----------------------------------");
                writer.printf("TỔNG CỘNG: %.0f VND\n", total);
                writer.println("==================================");
            }

            JOptionPane.showMessageDialog(this, "Hóa đơn đã được tạo: " + file.getAbsolutePath());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL khi tạo hóa đơn: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi IO khi tạo file hóa đơn: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        orderTable = new JTable();
        JButton exportBtn = new JButton();
        JButton refreshBtn = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ ĐƠN HÀNG");

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã đơn hàng", "Loại đơn", "Tổng tiền", "Ngày tạo", "Trạng thái"
            }
        ));
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                selectedRow = orderTable.getSelectedRow();
            }
        });
        jScrollPane1.setViewportView(orderTable);

        exportBtn.setText("Xuất hoá đơn");
        exportBtn.addActionListener(evt -> {
            if (selectedRow >= 0) {
                String orderID = orderList.get(selectedRow).getId();
                exportInvoice(orderID);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng trước!");
            }
        });

        refreshBtn.setText("Làm mới");
        refreshBtn.addActionListener(evt -> loadOrderData());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }

    private JTable orderTable;
    
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new OrderFrame().setVisible(true));
}

    
}
