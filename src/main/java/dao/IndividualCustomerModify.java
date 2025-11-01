/* Hans Nam */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Customer;
import models.IndividualCus;
import printstore_app.DBConnection;


public class IndividualCustomerModify {
    // lấy tất cả dữ liệu
public static List<IndividualCus> getIndividualCusList(String s) {
        List<IndividualCus> datalist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // kết nối db
           conn = DBConnection.getConnection();
            
            String sql = """
                    select c.CustomerID, i.ICName, i.Gender, c.Phone, c.Email, c.Address
                    from customers c
                    join individualcus i on c.CustomerID = i.IndividualID
                         """;
            if (s != null && !s.isEmpty()) {
                sql += " WHERE ICName like ?";
            }
            statement = conn.prepareStatement(sql);
            if (s != null && !s.isEmpty()) {
                statement.setString(1, s);
            }
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                IndividualCus ic = new IndividualCus (
                    resultSet.getString("Customerid"),
                    resultSet.getString("ICName"),
                    resultSet.getString("Gender"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"),
                    resultSet.getString("Address")
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

    public static void insert(IndividualCus c) {
        String sql1 = "INSERT INTO Customers (CustomerID, Phone, Email, Address) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO Individual_Cus (IndividualID, ICName, Gender) VALUES (?, ?, ?)";
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
                st2.setString(2, c.getIC_name());
                st2.setString(3, c.getGender());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Thêm khách hàng cá nhân thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void update (IndividualCus c) {
        String sql1 = "UPDATE Customers SET Phone=?, Email=?, Address=? WHERE Customer_ID=?";
        String sql2 = "UPDATE IndividualCus SET ICName=?, Gender=? WHERE IndividualID=?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, c.getPhone());
                st1.setString(2, c.getEmail());
                st1.setString(3, c.getAddress());
                st1.setString(4, c.getCustomer_ID());
                st1.executeUpdate();

                st2.setString(1, c.getIC_name());
                st2.setString(2, c.getGender());
                st2.setString(3, c.getCustomer_ID());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Cập nhật khách hàng cá nhân thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void delete (String id) {
        String sql1 = "DELETE FROM IndividualCus WHERE IndividualID = ?";
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
                System.out.println("Xóa khách hàng cá nhân: " + id);
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            }
        }

    public static Object getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
