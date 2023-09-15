package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private DataRepository data;

    public List<Account> getAll(){
        return data.getAll();
    }
}
