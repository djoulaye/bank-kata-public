package fr.ing.interview.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService implements AccountManagement {

    @Autowired
    private AccountService accountService;

    @Override
    public void createAccount(String accountNumber) {
        accountService.createAccount(accountNumber);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accountService.deleteAccount(accountNumber);
    }

}
