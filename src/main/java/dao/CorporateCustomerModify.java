/* Hans Nam */
package dao;

import java.sql.*;
import java.util.*;
import models.CorporateCus;
import printstore_app.DBConnection;

public class CorporateCustomerModify {
    public static List<CorporateCus> getCorporCusList(String s) {
        List<CorporateCus> datalist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // kết nối db
           conn = DBConnection.getConnection();
            
            String sql = """
                    select * from customers c
                    join corporatecus i on c.CustomerID = i.CorporateID
                         """;
            if (s != null && !s.isEmpty()) {
                sql += " WHERE CompanyName like ?";
            }
            statement = conn.prepareStatement(sql);
            if (s != null && !s.isEmpty()) {
                statement.setString(1, s);
            }
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                CorporateCus ic = new CorporateCus (
                    rs.getString("CustomerID"),
                    rs.getString("CompanyName"),
                    rs.getString("TaxCode"),
                    rs.getString("ContactPerson"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getString("Address")
                );
                datalist.add(ic);
            }
            // conn.commit();
         
        } catch (SQLException ex) {
            System.getLogger(CustomerModify.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    System.getLogger(CustomerModify.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.getLogger(CustomerModify.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }  
        return datalist;
    }
    // INSERT
    public static void insert(CorporateCus c) {
        String sql1 = "INSERT INTO Customers (CustomerID, Phone, Email, Address) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO Corporate_Cus (CorporateID, CompanyName, TaxCode, ContactPerson) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, c.getCustomer_ID());
                st1.setString(2, c.getPhone());
                st1.setString(3, c.getEmail());
                st1.setString(4, c.getAddress());
                st1.executeUpdate();

                st2.setString(1, c.getCustomer_ID());
                st2.setString(2, c.getCompanyName());
                st2.setString(3, c.getTaxCode());
                st2.setString(4, c.getContactPerson());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Thêm khách hàng doanh nghiệp thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void update(CorporateCus c) {
        String sql1 = "UPDATE Customers SET Phone=?, Email=?, Address=? WHERE CustomerID=?";
        String sql2 = "UPDATE CorporateCus SET CompanyName=?, TaxCode=?, ContactPerson = ? WHERE CorporateID=?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, c.getPhone());
                st1.setString(2, c.getEmail());
                st1.setString(3, c.getAddress());
                st1.setString(4, c.getCustomer_ID());
                st1.executeUpdate();

                st2.setString(1, c.getCompanyName());
                st2.setString(2, c.getContactPerson());
                st2.setString(3, c.getTaxCode());
                st2.setString(4, c.getCustomer_ID());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Cập nhật khách hàng doanh nghiệp thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void delete(String id) {
        String sql1 = "DELETE FROM CorporateCus WHERE CorporateID = ?";
        String sql2 = "DELETE FROM Customers WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, id);
                st1.executeUpdate();

                st2.setString(1, id);
                st2.executeUpdate();

                conn.commit();
                System.out.println("Xóa doanh nghiệp: " + id);
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

