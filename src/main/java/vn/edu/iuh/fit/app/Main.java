package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.AccountRepository;

public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();

        GrantAccess grantAccess = accountRepository.getAccountRole("dung123","123");
        System.out.println(grantAccess.toString());
//        List<Account> l = accountRepository.getAll();
//        for (Account acc :l
//             ) {
//                System.out.println(acc.toString());
//        }
    }
}
