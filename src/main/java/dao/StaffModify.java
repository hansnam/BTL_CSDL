/* Hans Nam */

package dao;


import java.sql.*;
import java.util.*;
import models.Staff;
import printstore_app.DBConnection;

public class StaffModify {
    public static List<Staff> getStaffList(String s) {
        List<Staff> datalist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // kết nối db
           conn = DBConnection.getConnection();
            
            String sql = """
                    select * from employees e
                    join staffs s on e.EmployeeID = s.StaffID
                         """;
            if (s != null && !s.isEmpty()) {
                sql += " WHERE EName like ?";
            }
            statement = conn.prepareStatement(sql);
            if (s != null && !s.isEmpty()) {
                statement.setString(1, s);
            }
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Staff st = new Staff (
                    rs.getString("EmployeeID"),
                    rs.getString("EName"),
                    rs.getString("Gender"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getInt("Salary"),
                    rs.getString("HireDate")
                );
                datalist.add(st);
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
   
    public static void insert(Staff s) {
        String sql1 = "INSERT INTO Employees (EmployeeID, EName, Gender, Phone, Email, Salary) VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Staffs (StaffID, HireDate) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, s.getID());
                st1.setString(2, s.getName());
                st1.setString(3, s.getGender());
                st1.setString(4, s.getPhone());
                st1.setString(5, s.getEmail());
                st1.setInt(6, s.getSalary());
                st1.executeUpdate();

                st2.setString(1, s.getID());
                st2.setString(2, s.getHireDate());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Thêm nhân viên thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void update(Staff s) {
        String sql1 = "UPDATE Employees SET EName=?, Gender=?, Phone=?, Email=?, Salary=? WHERE EmployeeID =?";
        String sql2 = "UPDATE Staffs SET HireDate=? WHERE StaffID=?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {
                
                st1.setString(1, s.getName());
                st1.setString(2, s.getGender());
                st1.setString(3, s.getPhone());
                st1.setString(4, s.getEmail());
                st1.setInt(5, s.getSalary());
                st1.setString(6, s.getID());
                st1.executeUpdate();

                st2.setString(1, s.getHireDate());
                st2.setString(2, s.getID());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Cập nhật nhân viên thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void delete(String id) {
        String sql1 = "DELETE FROM Staffs WHERE Staff_ID = ?";
        String sql2 = "DELETE FROM Employees WHERE Employee_ID = ?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, id);
                st1.executeUpdate();

                st2.setString(1, id);
                st2.executeUpdate();

                conn.commit();
                System.out.println("Xóa nhân viên: " + id);
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

