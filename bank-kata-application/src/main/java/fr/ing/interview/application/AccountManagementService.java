package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService implements AccountManagement {

    @Autowired
    private AccountService accountService;

    @Override
    public void createAccount(String accountNumber) {

    }

    @Override
    public void deleteAccount(String accountNumber) {

    }

    @Override
    public void updateAccount(Account account) {

    }
}
