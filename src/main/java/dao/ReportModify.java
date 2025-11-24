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
                + "(ReportID, ManagerID, StaffID, OrderQuantity, Revenue, Receivables, StartDate, EndDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, report.getReportID());
            ps.setString(2, report.getManagerID());
            ps.setString(3, report.getStaffID());
            ps.setInt(4, report.getOrderQuantity());
            ps.setInt(5, report.getRevenue());
            ps.setInt(6, report.getReceivables());
            ps.setDate(7, Date.valueOf(report.getStartDate()));
            ps.setDate(8, Date.valueOf(report.getEndDate()));
            
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
 
    public static List<Report> getReportList() {
        List<Report> dataList = new ArrayList<>();
        String sql = "SELECT * FROM Reports";

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
    
    public static Report createReport (LocalDate startDate, LocalDate endDate, String reportID, String managerID, String staffID) {
        Report newReport = null;
        String sql = """
                SELECT OrderStatus, COUNT(*) as soluong, SUM(TotalAmount) as tong 
                FROM Orders WHERE OrderDate >= ? AND OrderDate <= ?
                GROUP BY OrderStatus
                ORDER BY OrderStatus
            """;
        try (Connection conn = DBConnection.getConnection()) {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));
            ResultSet rs = ps.executeQuery();
            int soluong = 0;
            int loinhuan = 0;
            int congno = 0;
            if(rs.next()) {
                soluong += rs.getInt("soluong");
                loinhuan = rs.getInt("tong");
            }
            if(rs.next()) {
                soluong += rs.getInt("soluong");
                congno = rs.getInt("tong");
            }
            newReport = new Report( reportID, managerID, staffID, soluong, loinhuan, congno, startDate, endDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return newReport;
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
                SUM(od.SubTotal) AS moneySell
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
