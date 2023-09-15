package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.DataRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRepository databaseRepository = new DataRepository();
        databaseRepository.getConnection();
        List<Account> l = databaseRepository.getAll();
        for (Account account: l
        ) {
            System.out.println(account.toString());
        }
    }
}
