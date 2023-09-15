package vn.edu.iuh.fit.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    // Thông tin kết nối đến cơ sở dữ liệu
    private static final String URL = "jdbc:mariadb://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sapassword";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Đăng ký driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // Thiết lập kết nối
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Kiểm tra xem kết nối có đang hoạt động hay không
            if (connection.isValid(5)) {
                System.out.println("Kết nối đến cơ sở dữ liệu là hợp lệ.");
            } else {
                System.err.println("Kết nối đến cơ sở dữ liệu không hợp lệ.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Không thể kết nối đến cơ sở dữ liệu.");
        } finally {
            // Đóng kết nối
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

