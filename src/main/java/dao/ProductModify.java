/* Hans Nam */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;
import printstore_app.DBConnection;

public class ProductModify {

    // 🔹 Lấy danh sách sản phẩm (tìm kiếm theo tên nếu có chuỗi nhập vào)
    public static List<Product> getProductList(String keyword) {
        List<Product> dataList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        if (keyword != null && !keyword.isEmpty()) {
            sql += " WHERE ProductName LIKE ?";
        }

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("Descriptions")
                );
                dataList.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataList;
    }

    // 🔹 Thêm sản phẩm mới
    public static void insert(Product product) {
        String sql = "INSERT INTO products(ProductID, ProductName, Price, Descriptions) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getP_id());
            ps.setString(2, product.getP_name());
            ps.setDouble(3, product.getP_price());
            ps.setString(4, product.getP_descript());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // 🔹 Cập nhật thông tin sản phẩm
    public static void update(Product product) {
        String sql = "UPDATE products SET ProductName = ?, Price = ?, Descriptions = ? WHERE ProductID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getP_name());
            ps.setDouble(2, product.getP_price());
            ps.setString(3, product.getP_descript());
            ps.setString(4, product.getP_id());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xóa sản phẩm
    public static void delete(String productId) {
        String sql = "DELETE FROM products WHERE ProductID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductModify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getProductIdByName(String productName) {
        String productId = null;
        String sql = "SELECT ProductID FROM products WHERE ProductName = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, productName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productId = rs.getString("ProductID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productId;
    }

    public static double getProductPriceById(String productId) {
        String sql = "SELECT Price FROM products WHERE ProductID = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
