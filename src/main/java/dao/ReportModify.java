package dao;

import printstore_app.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportModify {

    // Doanh thu tổng theo ngày (trả map: order_id -> total) hoặc ngày -> total
    public static Map<String, Double> revenueByDate(LocalDate date) {
        Map<String, Double> result = new LinkedHashMap<>();
        String sql = "SELECT id, total FROM orders WHERE DATE(created_at)=? AND status='PAID' ORDER BY created_at";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.put("Order #" + rs.getInt("id"), rs.getDouble("total"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // Doanh thu theo tháng -> trả map: ngày -> tổng trong ngày
    public static Map<String, Double> revenueByMonth(int year, int month) {
        Map<String, Double> result = new LinkedHashMap<>();
        String sql = "SELECT DATE(created_at) as ngay, SUM(total) as tong " +
                     "FROM orders WHERE status='PAID' AND YEAR(created_at)=? AND MONTH(created_at)=? " +
                     "GROUP BY DATE(created_at) ORDER BY DATE(created_at)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.put(rs.getDate("ngay").toString(), rs.getDouble("tong"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
