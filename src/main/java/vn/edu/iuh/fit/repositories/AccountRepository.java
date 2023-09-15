package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.entities.Account;

import java.util.List;

public class AccountRepository {
    private DataRepository data;
    public AccountRepository(){
        data = new DataRepository();
    }
    public List<Account> getAll(){
        return data.getAll();
    }
}
