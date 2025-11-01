/* Hans Nam */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import models.Order;
import models.OrderDetail;
import printstore_app.DBConnection;


public class OrderModify {
    public static List<Order> getOrderList() {
        List<Order> datalist = new ArrayList<>();
        String sql = """
                     select OrderID, CustomerID, QuantityType, TotalAmount, OrderDate, OrderStatus
                     from Orders
                     order by Order_Dtae DESC
                     """;
        try(Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Order o = new Order();
                o.setId(rs.getString("OrderID"));
                o.setCus_id(rs.getString("CustomerID"));
                o.setQuantityType(rs.getInt("QuantityType"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));
                Timestamp t = rs.getTimestamp("OrderDate");
                if(t != null) o.setDate(t.toLocalDateTime().toString());
                o.setStatus(rs.getString("OrderStatus"));
                datalist.add(o);
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return datalist;
    }
    
    public static List<OrderDetail> getOrderDetailList(String orderId) {
        List<OrderDetail> datalist = new ArrayList<>();
        String sql = """
                     select p.ProductName as product_name, od.Quantity, p.Price, od.SubTotal  
                     from orderdetail od join  products p
                     on od.ProductID = p.ProductID
                     where od.OrderID = ?
                     """;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, orderId);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    OrderDetail d = new OrderDetail();
                    d.setProductName(rs.getString("product_name"));
                    d.setQuantity(rs.getInt("Quantity"));
                    d.setPrice(rs.getDouble("Price"));
                    datalist.add(d);
                }
            }
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }
     public static ResultSet getOrderDetail(String orderID) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = """
                     SELECT p.ProductName, od.Quantity, p.Price, (od.Quantity * p.Price) AS SubTotal
                     FROM orderdetail od 
                     JOIN products p ON od.ProductID = p.ProductID
                     WHERE od.OrderID = ?
                     """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, orderID);
        return ps.executeQuery();
    }
     
    public static boolean isOrderIdExists(String orderId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String generateUniqueOrderId() {
        Random random = new Random();
        String orderId;
        do {
            int number = 1000000 + random.nextInt(9000000); // 7 chữ số ngẫu nhiên
            orderId = "ORD" + number;
        } while (isOrderIdExists(orderId)); // Lặp cho đến khi không trùng
        return orderId;
    }    
     
     
}
