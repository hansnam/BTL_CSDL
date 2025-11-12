/* Hans Nam */

package printstore_app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements Config {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Kết nối MySQL thành công!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi kết nối MySQL: " + e.getMessage());
        }
        return conn;
    }
}




/*

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                        Config.getConnectionString(),
                        Config.DB_USER,
                        Config.DB_PASSWORD
                );
                System.out.println("Kết nối MySQL thành công!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy Driver MySQL: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Lỗi kết nối MySQL: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
                System.out.println("Đã đóng kết nối!");
            }
        } catch (SQLException e) {
            System.err.println("Không thể đóng kết nối: " + e.getMessage());
        }
    }
}
*/