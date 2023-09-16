package vn.edu.iuh.fit.repositories;

import org.mariadb.jdbc.Connection;
import vn.edu.iuh.fit.entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository() {
        connection = DataRepository.getConnection();
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * from mydb.account");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Account getAccount(String name, String password) {

        PreparedStatement statement = null;
        Account ac = null;
        try {
            statement = connection.prepareStatement("SELECT mydb.account.* , mydb.grant_access.note FROM mydb.account INNER JOIN mydb.grant_access ON account.account_id = grant_access.account_id  where account.account_id = ? and password = ?");
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
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
                ac = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        status);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ac;
    }

    public GrantAccess getAccountRole(String username, String password) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT account.*, role.role_id, role.role_name, grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id INNER JOIN mydb.role ON grant_access.role_id = role.role_id WHERE account.account_id = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String statusValueA = rs.getString(6);
                    Status statusA = getStatusFromValue(statusValueA);

                    String isGrantValue = rs.getString("grant_access.is_grant");
                    IsGrant isGrant = getIsGrantFromValue(isGrantValue);

                    Account account = new Account(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            statusA
                    );

                    Role role = new Role(
                            rs.getString("role.role_id"),
                            rs.getString("role.role_name")
                    );

                    return new GrantAccess(account, role, isGrant);
                } else {
                    // Trả về null hoặc xử lý trường hợp không tìm thấy tài khoản
                    return null;
                }
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ một cách cụ thể hoặc trả về giá trị mặc định nếu cần
            throw new RuntimeException(e);
        }
    }

    private Status getStatusFromValue(String value) {
        switch (value) {
            case "1":
                return Status.Active;
            case "0":
                return Status.Deactive;
            case "-1":
                return Status.Delete;
            default:
                return null; // Xử lý trường hợp giá trị không hợp lệ
        }
    }

    private IsGrant getIsGrantFromValue(String value) {
        switch (value) {
            case "1":
                return IsGrant.ENABLE;
            case "0":
                return IsGrant.DISABLE;
            case "-1":
                return IsGrant.DELETE;
            default:
                return null; // Xử lý trường hợp giá trị không hợp lệ
        }
    }


    public List<GrantAccess> getDsAccountRole() {

        PreparedStatement statement = null;
        List<GrantAccess> dsAccount = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT mydb.account.*,mydb.role.role_id,mydb.role.role_name, mydb.grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id where account.account_id = ? and password = ?");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValueA = rs.getString(6);
                Status statusA = null;
                switch (statusValueA) {
                    case "1":
                        statusA = Status.Active;
                        break;
                    case "0":
                        statusA = Status.Deactive;
                        break;
                    case "-1":
                        statusA = Status.Delete;
                        break;
                    default:

                        break;
                }
                String isGrantValue = rs.getString(9);
                IsGrant isGrant = null;
                switch (isGrantValue) {
                    case "1":
                        isGrant = IsGrant.ENABLE;
                        break;
                    case "0":
                        isGrant = IsGrant.DISABLE;
                        break;
                    case "-1":
                        isGrant = IsGrant.DELETE;
                        break;
                    default:

                        break;
                }
                GrantAccess grantAccess = new GrantAccess(new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        statusA),
                        new Role(rs.getString(7), rs.getString(8)),
                       isGrant);
                dsAccount.add(grantAccess);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsAccount;
    }
    public List<GrantAccess> getDsAccount() {

        PreparedStatement statement = null;
        List<GrantAccess> dsAccount = new ArrayList<>();
        String s = "admin";
        try {
            statement = connection.prepareStatement("SELECT mydb.account.*,mydb.role.role_id,mydb.role.role_name, mydb.grant_access.is_grant FROM mydb.grant_access INNER JOIN mydb.account ON grant_access.account_id = account.account_id \n" + "INNER JOIN mydb.role ON grant_access.role_id = role.role_id " +
                    "where grant_access.role_id !='" + s + "'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String statusValueA = rs.getString(6);
                Status statusA = null;
                switch (statusValueA) {
                    case "1":
                        statusA = Status.Active;
                        break;
                    case "0":
                        statusA = Status.Deactive;
                        break;
                    case "-1":
                        statusA = Status.Delete;
                        break;
                    default:

                        break;
                }
                String isGrantValue = rs.getString(9);
                IsGrant isGrant = null;
                switch (isGrantValue) {
                    case "1":
                        isGrant = IsGrant.ENABLE;
                        break;
                    case "0":
                        isGrant = IsGrant.DISABLE;
                        break;
                    case "-1":
                        isGrant = IsGrant.DELETE;
                        break;
                    default:

                        break;
                }
                GrantAccess grantAccess = new GrantAccess(new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                       statusA), new Role(rs.getString(7), rs.getString(8)),
                       isGrant);
                dsAccount.add(grantAccess);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsAccount;
    }
    public Account accountLogin(String name, String pass) {
        Account ac = null;
        try {
            // Truy vấn để tìm tài khoản dựa trên name và pass
            String selectQuery = "SELECT * FROM mydb.account WHERE account_id = ? AND password = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            selectStatement.setString(2, pass);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE mydb.account SET status = 1 WHERE account_id = ? AND password = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, name);
                updateStatement.setString(2, pass);
                updateStatement.executeUpdate();
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
                ac = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ac;
    }

    public boolean accountLogout(String userName) {
        try {
            String updateQuery = "UPDATE mydb.account SET status = 0 WHERE account_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, userName);
            updateStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }
    public boolean addAccount(Account acc) {
        PreparedStatement statement = null;

        try {
            int status = 0;
            if (acc.getStatus() == Status.Active) {
                status=1;
            } else if (acc.getStatus() == Status.Deactive) {
                status= 0;
            } else if (acc.getStatus()  == Status.Delete) {
                status= -1;
            }
            statement = connection.prepareStatement("INSERT INTO mydb.account VALUES(?,?,?,?,?,?)");
            statement.setString(1, acc.getAccount_id());
            statement.setString(2, acc.getFull_name());
            statement.setString(3, acc.getPassword());
            statement.setString(4, acc.getEmail());
            statement.setString(5, acc.getPhone());
            statement.setInt(6,status);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            System.err.println("Add account success!!!!");

        } else {
            System.err.println("Add account false!!!!");
        }
        return true;
    }

    public boolean delAccountById(String id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update mydb.account set status=-1 where account_id=?");
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateAccount(Account acc) {
        PreparedStatement statement = null;

        try {
            int status = 0;
            if (acc.getStatus() == Status.Active) {
                status=1;
            } else if (acc.getStatus() == Status.Deactive) {
                status= 0;
            } else if (acc.getStatus()  == Status.Delete) {
                status= -1;
            }
            statement = connection.prepareStatement("update mydb.account set full_name=?,password=?,email=?,phone=?,status=? where account_id=?");
            statement.setString(1, acc.getFull_name());
            statement.setString(2, acc.getPassword());
            statement.setString(3, acc.getEmail());
            statement.setString(4, acc.getPhone());
            statement.setInt(5, status);
            statement.setString(6, acc.getAccount_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (statement != null) {
            System.err.println("Update account success!!!");
        } else {
            System.err.println("Update account false!!!");
        }
        return true;
    }
}