package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.util.List;

public class AccountServices {
    @Inject
    private AccountRepository repository;
    public List<Account> getAll(){
        return repository.getAll();
    }
}
