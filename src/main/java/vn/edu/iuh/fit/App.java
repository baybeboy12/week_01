package vn.edu.iuh.fit;

import vn.edu.iuh.fit.repositories.DataRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306/mydb";
        try {
            connection = DriverManager.getConnection(url, "root", "sapassword");
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection != null) {
            System.err.println("Success!!!!!");

        }
    }
}