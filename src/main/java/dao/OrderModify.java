/* Hans Nam */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import models.Order;
import models.OrderDetail;
import printstore_app.DBConnection;

public class OrderModify {

    public static List<Order> getOrderList() {
        List<Order> datalist = new ArrayList<>();
        String sql = """
                    select OrderID, CustomerID, OrderDate, OrderStatus
                    from Orders
                    order by OrderDate DESC
                    """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setID(rs.getString("OrderID"));
                o.setCusID(rs.getString("CustomerID"));
                Timestamp t = rs.getTimestamp("OrderDate");
                if (t != null) {
                    o.setDate(t.toLocalDateTime().toString());
                }
                o.setStatus(rs.getString("OrderStatus"));
                datalist.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }

    public static List<OrderDetail> getOrderDetailList(String orderId) {
        List<OrderDetail> datalist = new ArrayList<>();
        String sql = """
                    select p.ProductName, od.Quantity, p.Price, (od.Quantity * p.Price) AS SubTotal  
                    from orderdetail od join  products p
                    on od.ProductID = p.ProductID
                    where od.OrderID = ?
                    """;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail d = new OrderDetail();
                    d.setQuantity(rs.getInt("Quantity"));
                    d.setPrice(rs.getInt("Price"));
                    datalist.add(d);
                }
            }
        } catch (Exception e) {
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
        String sql = "SELECT COUNT(*) FROM orders WHERE OrderID = ?";
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

    public static List<Object[]> getOrderDetailForTable(String orderId) {
        List<Object[]> orderDetailsList = new ArrayList<>();

        String sql = "SELECT p.ProductName,p.Price, od.Quantity, (od.Quantity * p.Price) AS SubTotal "
                + "FROM orderdetail od JOIN products p ON od.ProductID = p.ProductID "
                + "WHERE od.OrderID = ?";

        try (Connection conn = DBConnection.getConnection(); // Giả sử DBConnection.getConnection() là đúng
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                int cnt = 1;
                while (rs.next()) {
                    Object[] row = new Object[5];
                    row[0] = cnt++;
                    row[1] = rs.getString("ProductName");
                    row[2] = rs.getString("Price");
                    row[3] = rs.getInt("Quantity");
                    row[4] = rs.getInt("SubTotal");

                    orderDetailsList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderDetailsList;
    }

    public static List<Object[]> getAllOrdersForTable() {
        List<Object[]> orderList = new ArrayList<>();

        String sql = """
            SELECT 
                    o.OrderID,
                    o.CustomerID,
                    COUNT(DISTINCT od.ProductID) AS ProductTypes,
                    SUM(od.Quantity * p.Price) AS TotalAmount,
                    o.OrderDate,
                    o.OrderStatus
                FROM 
                    orders o
                LEFT JOIN 
                    orderdetail od ON o.OrderID = od.OrderID
                LEFT JOIN 
                    products p ON od.ProductID = p.ProductID
                GROUP BY 
                    o.OrderID, o.CustomerID, o.OrderDate, o.OrderStatus
                ORDER BY 
                    o.OrderDate DESC
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            int cnt = 1;
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = cnt++;
                row[1] = rs.getString("OrderID");
                row[2] = rs.getString("CustomerID");
                row[3] = rs.getInt("ProductTypes");
                row[4] = rs.getInt("TotalAmount");
                row[5] = rs.getTimestamp("OrderDate");
                row[6] = rs.getString("OrderStatus");
                orderList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public static boolean insertOrder(String orderId, String customerId, int totalAmount, java.sql.Timestamp orderDate, String status, String ManagerID, int QuantityType) {
        String sql = "INSERT INTO orders (OrderID, CustomerID,OrderDate, OrderStatus, ManagerID) VALUES (?, ?, ?, ?, ?)";

        //boolean orderSaved = OrderModify.insertOrder(orderId, customerId, totalAmount, orderDate, status, managerId);
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ps.setString(2, customerId);
            // ps.setInt(3, totalAmount);
            ps.setTimestamp(3, orderDate);
            ps.setString(4, status);
            ps.setString(5, ManagerID);
            //ps.setInt(6, QuantityType);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertOrderDetail(String orderId, String productId, int quantity) {
        String sql = "INSERT INTO orderdetail (OrderID, ProductID, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ps.setString(2, productId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object[]> findOrderById(String orderId) {
        List<Object[]> orderList = new ArrayList<>();

        String sql = """
        SELECT 
            o.OrderID, 
            o.CustomerID, 
            COUNT(DISTINCT od.ProductID) AS ProductTypes, 
            SUM(od.Quantity * p.Price) AS TotalAmount, 
            o.OrderDate, 
            o.OrderStatus 
        FROM orders o 
        JOIN orderdetail od ON o.OrderID = od.OrderID 
        JOIN products p ON od.ProductID = p.ProductID 
        WHERE o.OrderID LIKE ? 
        GROUP BY o.OrderID, o.CustomerID, o.OrderDate, o.OrderStatus 
        ORDER BY o.OrderDate DESC
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + orderId + "%"); // tìm kiếm gần đúng

            ResultSet rs = stmt.executeQuery();

            int index = 1;
            while (rs.next()) {
                Object[] row = {
                    index++, // STT
                    rs.getString("OrderID"),
                    rs.getString("CustomerID"),
                    rs.getInt("ProductTypes"),
                    rs.getInt("TotalAmount"),
                    rs.getDate("OrderDate"),
                    rs.getString("OrderStatus")
                };
                orderList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public static List<Object[]> findOrderByCusId(String orderId) {
        List<Object[]> orderList = new ArrayList<>();

        String sql = """
        SELECT 
            o.OrderID, 
            o.CustomerID, 
            COUNT(DISTINCT od.ProductID) AS ProductTypes, 
            SUM(od.Quantity * p.Price) AS TotalAmount, 
            o.OrderDate, 
            o.OrderStatus 
        FROM orders o 
        JOIN orderdetail od ON o.OrderID = od.OrderID 
        JOIN products p ON od.ProductID = p.ProductID 
        WHERE o.CustomerID LIKE ? 
        GROUP BY o.OrderID, o.CustomerID, o.OrderDate, o.OrderStatus 
        ORDER BY o.OrderDate DESC
        """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + orderId + "%"); // tìm kiếm gần đúng

            ResultSet rs = stmt.executeQuery();

            int index = 1;
            while (rs.next()) {
                Object[] row = {
                    index++, // STT
                    rs.getString("OrderID"),
                    rs.getString("CustomerID"),
                    rs.getInt("ProductTypes"),
                    rs.getInt("TotalAmount"),
                    rs.getDate("OrderDate"),
                    rs.getString("OrderStatus")
                };
                orderList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

}
