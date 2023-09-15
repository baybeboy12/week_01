package vn.edu.iuh.fit.Database;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Status;

import java.sql.*;
import java.util.ArrayList;

public class ConnectDB {
    private static Connection connection=null;

    public Connection getConnection(){
        String url = "jdbc:mariadb://localhost:3306/mydb";
        try {
            connection = DriverManager.getConnection(url, "root", "sapassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  connection;
    }

    public ArrayList<Account> getAllAccoumts(){
        PreparedStatement statement = null;
        ArrayList<Account> arrayList = new ArrayList<>();

        try {
            statement = connection.prepareStatement("select * from account");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
            String statusValues = rs.getString(6);
            Status status = null;
            switch (statusValues){
                case "1":
                    status =Status.Active;
                    break;
                case "0":
                    status = Status.Deactive;
                    break;
                case "-1" :
                    status = Status.Delete;
                    break;
                default:
                    break;
            }
                Account account = new Account(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    status
            );
            arrayList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

           return arrayList;
    }


}
