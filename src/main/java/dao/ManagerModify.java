/* Hans Nam */

package dao;

import java.sql.*;
import java.util.*;
import models.Manager;
import printstore_app.DBConnection;

public class ManagerModify {    
    
    public static List<Manager> getManagerList(String s) {
        List<Manager> datalist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // kết nối db
           conn = DBConnection.getConnection();
            
            String sql = """
                    select * from employees e
                    join managers m on e.EmployeeID = m.ManagerID
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
                Manager m = new Manager (
                    rs.getString("EmployeeID"),
                    rs.getString("EName"),
                    rs.getString("Gender"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getInt("Salary"),
                    rs.getString("Title")
                );
                datalist.add(m);
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
    
    public static void insert(Manager m) {
        String sql1 = "INSERT INTO Emloyees (EmployeeID, EName, Gender, Phone, Email, Salary) VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Managers (StaffID, HireDate, ManagerID) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, m.getID());
                st1.setString(2, m.getName());
                st1.setString(2, m.getGender());
                st1.setString(4, m.getPhone());
                st1.setString(5, m.getEmail());
                st1.setInt(6, m.getSalary());
                st1.executeUpdate();

                st2.setString(1, m.getID());
                st2.setString(2, m.getTitle());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Thêm quản lý thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(Manager m) {
        String sql1 = "UPDATE Employees SET EName=?, Gender=?, Phone=?, Email=?, Salary=? WHERE EmployeeID =?";
        String sql2 = "UPDATE Managers SET Title=? WHERE ManagerID=?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {
                
                st1.setString(1, m.getName());
                st1.setString(2, m.getGender());
                st1.setString(3, m.getPhone());
                st1.setString(4, m.getEmail());
                st1.setInt(5, m.getSalary());
                st1.setString(6, m.getID());
                st1.executeUpdate();

                st2.setString(1, m.getTitle());
                st2.setString(2, m.getID());
                st2.executeUpdate();

                conn.commit();
                System.out.println("Cập nhật quản lý thành công!");
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(String id) {
        String sql1 = "DELETE FROM Managers WHERE ManangerID = ?";
        String sql2 = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement st1 = conn.prepareStatement(sql1);
                 PreparedStatement st2 = conn.prepareStatement(sql2)) {

                st1.setString(1, id);
                st1.executeUpdate();

                st2.setString(1, id);
                st2.executeUpdate();

                conn.commit();
                System.out.println("Xóa quản lý: " + id);
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<String> getManagerIDList() {
        List<String> dataList = new ArrayList<>();

        String sql = "SELECT ManagerID FROM managers";

        try (Connection conn = DBConnection.getConnection()){
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataList.add(rs.getString("ManagerID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    public static Manager getManagerByID(String managerID) {
        
        try (Connection conn = DBConnection.getConnection()){
            
            String sql = """
                select * from employees e
                join managers m on e.EmployeeID = m.ManagerID
                where e.EmployeeID = ?
            """;
          
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, managerID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Manager (
                    rs.getString("EmployeeID"),
                    rs.getString("EName"),
                    rs.getString("Gender"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getInt("Salary"),
                    rs.getString("Title")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}

