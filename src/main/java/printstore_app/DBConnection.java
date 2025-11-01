/* Hans Nam */

package printstore_app;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sale_management";
    private static final String USER = "root";
    private static final String PASSWORD = "123456"; // nếu có mật khẩu MySQL thì điền vào

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối MySQL thành công!");
        } catch (Exception e) {
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