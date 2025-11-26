package dao;

import printstore_app.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Report;

public class ReportModify {
    
    public static void insert (Report report) {
        String sql = "INSERT INTO reports "
                + "(ReportID, ManagerID, StaffID, StartDate, EndDate) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, report.getReportID());
            ps.setString(2, report.getManagerID());
            ps.setString(3, report.getStaffID());
            ps.setDate(4, Date.valueOf(report.getStartDate()));
            ps.setDate(5, Date.valueOf(report.getEndDate()));
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void delete (String reportID) {
        String sql = "DELETE FROM reports WHERE ReportID = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reportID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
    public static Report getReportByReportID(String reportID) {
        String sql = """
                    SELECT 
                        r.ReportID,
                        r.ManagerID,
                        r.StaffID,
                        r.StartDate,
                        r.EndDate,

                        COUNT(DISTINCT o.OrderID) AS OrderQuantity,

                        IFNULL(SUM(CASE 
                            WHEN o.OrderStatus = 'Đã hoàn thành' 
                            THEN od.Quantity * p.Price 
                            ELSE 0 
                        END), 0) AS Revenue,
                     
                        IFNULL(SUM(CASE 
                            WHEN o.OrderStatus = 'Đang xử lý' 
                            THEN od.Quantity * p.Price 
                            ELSE 0 
                        END), 0) AS Receivables
                    FROM 
                        Reports AS r
                    JOIN 
                        Orders AS o ON r.StaffID = o.StaffID AND o.OrderDate BETWEEN r.StartDate AND r.EndDate
                    JOIN 
                        OrderDetail AS od ON o.OrderID = od.OrderID
                    JOIN 
                        Products AS p ON od.ProductID = p.ProductID
                    WHERE
                        r.ReportID = ?
                    GROUP BY 
                        r.ReportID, r.ManagerID, r.StaffID, r.StartDate, r.EndDate
                     
                    """;
        Report report = null;
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, reportID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                report = new Report(
                        rs.getString("ReportID"),
                        rs.getString("ManagerID"),
                        rs.getString("StaffID"),
                        rs.getInt("OrderQuantity"),
                        rs.getInt("Revenue"),
                        rs.getInt("Receivables"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate()
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return report;
    }
    
    public static List<Report> getReportList() {
        List<Report> dataList = new ArrayList<>();
        String sql = """
                    SELECT 
                        r.ReportID,
                        r.ManagerID,
                        r.StaffID,
                        r.StartDate,
                        r.EndDate,

                        COUNT(DISTINCT o.OrderID) AS OrderQuantity,

                        IFNULL(SUM(CASE 
                            WHEN o.OrderStatus = 'Đã hoàn thành' 
                            THEN od.Quantity * p.Price 
                            ELSE 0 
                        END), 0) AS Revenue,
                     
                        IFNULL(SUM(CASE 
                            WHEN o.OrderStatus = 'Đang xử lý' 
                            THEN od.Quantity * p.Price 
                            ELSE 0 
                        END), 0) AS Receivables
                    FROM 
                        Reports AS r
                    JOIN 
                        Orders AS o ON o.OrderDate BETWEEN r.StartDate AND r.EndDate
                    JOIN 
                        OrderDetail AS od ON o.OrderID = od.OrderID
                    JOIN 
                        Products AS p ON od.ProductID = p.ProductID
                    GROUP BY 
                        r.ReportID, r.ManagerID, r.StaffID, r.StartDate, r.EndDate
                     
                    """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Report p = new Report(
                        rs.getString("ReportID"),
                        rs.getString("ManagerID"),
                        rs.getString("StaffID"),
                        rs.getInt("OrderQuantity"),
                        rs.getInt("Revenue"),
                        rs.getInt("Receivables"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate()
                );
                dataList.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    public static String getNextReportID () {
        List<String> reportIDList = ReportModify.getReportIDList();
        reportIDList.sort((a, b) -> a.compareTo(b));
        String reportIDLast = reportIDList.get(reportIDList.size() - 1);
        int lastID = Integer.parseInt(reportIDLast.substring(1));
        System.out.println(lastID);
        return String.format("R%d", lastID + 1);
    }
        
    public static List<String> getReportIDList() {
        List<String> dataList = new ArrayList<>();

        String sql = "SELECT ReportID FROM reports";

        try (Connection conn = DBConnection.getConnection()){
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataList.add(rs.getString("ReportID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    public static List<Object[]> getReportDetail (LocalDate startDate, LocalDate endDate) {
        List<Object[]> dataList = new ArrayList<>();

        String sql = """
            SELECT 
                p.ProductID as productID,
                p.ProductName AS ProductName,
                SUM(od.Quantity) AS amountSell,
                p.Price as unitPrice,
                SUM(od.Quantity * p.Price) AS moneySell
            FROM (  
                    SELECT * FROM Orders 
                    WHERE OrderDate >= ? AND OrderDate <= ? AND OrderStatus = 'Đã hoàn thành'     
                ) AS o
            JOIN 
                OrderDetail od ON o.OrderID = od.OrderID
            JOIN
                Products p ON od.ProductID = p.ProductID
            GROUP BY 
                p.ProductID, p.ProductName, p.Price
            ORDER BY
                moneySell
        """;
        try (Connection conn = DBConnection.getConnection()){
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, startDate.toString());
            ps.setString(2, endDate.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getString("ProductName");
                row[1] = rs.getInt("amountSell");
                row[2] = rs.getInt("unitPrice");
                row[3] = rs.getInt("moneySell");
                dataList.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
}
