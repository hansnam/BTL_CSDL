/* Hans Nam */

package printstore_app;

import java.sql.Connection;


public class Main {
public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công tới cơ sở dữ liệu " + Config.DB_NAME);
        } else {
            System.out.println("Kết nối thất bại!");
        }
        // DBConnection.closeConnection();
    }
}
