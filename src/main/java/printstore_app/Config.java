/* Hans Nam */

package printstore_app;


public interface Config {
    String DB_URL = "jdbc:mysql://localhost:3306/sale_management";
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "sale_management";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "123456";

    // Thông tin ứng dụng
    public static final String APP_TITLE = "Phần mềm quản lý cửa hàng in ấn";
    public static final String CURRENCY = "VND";

    /**
     * Hàm trả về chuỗi kết nối JDBC hoàn chỉnh
     * @return 
     */
    public static String getConnectionString() {
        return "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
                + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    }
}
