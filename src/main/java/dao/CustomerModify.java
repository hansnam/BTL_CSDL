/* Hans Nam */

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Customer;
import printstore_app.DBConnection;

public class CustomerModify {

    //Lấy danh sách khách hàng
    public static List<Customer> getListCustomer() {
        List<Customer> datalist = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {


            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString("CustomerID"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address")
                );
                datalist.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }

    //Thêm khách hàng mới
    public static void insert(Customer c) {
        String sql = "INSERT INTO Customers (CustomerID, CustomerName, Phone, Email, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Cập nhật thông tin khách hàng
    public static void update(Customer c) {
        String sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getPhone());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getCustomerID());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Xóa khách hàng theo ID
    public static void delete(String customerId) {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customerId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static String getCustomerNameById(String customerId) {
        String name = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            
            String sql = """
                SELECT ICName AS CustomerName FROM individualCus WHERE IndividualID = ?
                UNION
                SELECT CompanyName AS CustomerName FROM corporateCus WHERE CorporateID = ?
            """;

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setString(2, customerId);

            rs = stmt.executeQuery();
            if (rs.next()) {
                name = rs.getString("CustomerName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ignored) {
                
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignored) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ignored) {

            }
        }
        return name;
    }
    
    public static String getCustomerAddressById(String customerId) {
        String address = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            
            String sql = """
            SELECT Address FROM customers WHERE CustomerID = ?
                    """;

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            

            rs = stmt.executeQuery();
            if (rs.next()) {
                address = rs.getString("Address");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ignored) {

            }
            try {
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException ignored) {

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ignored) {

            }
        }
        return address;
    }
    
    public static boolean isCustomerIdExists(String customerId) {
        String sql = "SELECT COUNT(*) FROM Customers WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
