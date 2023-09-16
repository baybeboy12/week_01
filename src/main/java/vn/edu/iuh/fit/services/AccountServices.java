package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.util.List;

public class AccountServices {
    @Inject
    private AccountRepository repository;
    public AccountServices(){
        repository = new AccountRepository();
    }

    public List<Account> getAll(){
        return repository.getAll();
    }
    public Account getAccount(String name, String password){
        return repository.getAccount(name, password);
    }
    public GrantAccess getAccountRole(String username, String password) {
        return repository.getAccountRole(username, password);
    }
    public List<GrantAccess> getDsAccount() {
        return repository.getDsAccountRole();
    }
    public Account accountLogin(String name, String pass){
        return repository.accountLogin(name,pass);
    }
}
