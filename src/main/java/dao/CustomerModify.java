/* Hans Nam */

package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Customer;
import printstore_app.DBConnection;

public class CustomerModify {

    // 🔹 Lấy danh sách khách hàng
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

    // 🔹 Thêm khách hàng mới
    public static void insert(Customer c) {
        String sql = "INSERT INTO Customers (CustomerID, CustomerName, Phone, Email, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCustomer_ID());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Cập nhật thông tin khách hàng
    public static void update(Customer c) {
        String sql = "UPDATE Customers SET CustomerName = ?, Phone = ?, Email = ?, Address = ? WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getPhone());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getCustomer_ID());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 Xóa khách hàng theo ID
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
}
