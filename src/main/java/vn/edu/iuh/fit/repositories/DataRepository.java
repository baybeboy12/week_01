package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private Connection connection=null;

    public org.mariadb.jdbc.Connection getConnection(){
        String url = "jdbc:mariadb://localhost:3306/mydb";
        try {
            connection = DriverManager.getConnection(url, "root", "sapassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (org.mariadb.jdbc.Connection) connection;
    }
    public List<Account> getAll(){

        PreparedStatement statement = null;
        List<Account> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * from mydb.account");
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                String statusValue = rs.getString(6);
                Status status = null;
                switch (statusValue) {
                    case "1":
                        status = Status.Active;
                        break;
                    case "0":
                        status = Status.Deactive;
                        break;
                    case "-1":
                        status = Status.Delete;
                        break;
                    default:

                        break;
                }
                Account account = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        status);
                list.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean addAccount(Account account){
        PreparedStatement statement = null;
        try {
            int n= 1;
          String sql = "INSERT INTO account (account_id, full_name,password,email,phone,status) " +
                    "VALUES (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getAccount_id());
            statement.setString(2, account.getFull_name());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getPhone());
            statement.setInt(6, n);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return false;
    }
}
