package com.bellagio.application;

import com.bellagio.application.exception.UnknownAccountException;
import com.bellagio.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountInformationService implements AccountInformation {

    @Autowired
    private AccountService accountService;

    @Override
    public double getBalanceOfAccount(String accountNumber) throws UnknownAccountException {
        double balance;
        //TODO ne devrait pas être nécessaire mais l'exception ne se propage pas sinon :/
        try {
            balance = accountService.getAccount(accountNumber).getBalance();
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException(e.getMessage());
        }
        return balance;
    }

    @Override
    public Account getHistoryOfAccount(String accountNumber) {
        Account account;
        //TODO ne devrait pas être nécessaire mais l'exception ne se propage pas sinon :/
        try {
            account = accountService.getAccount(accountNumber);
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException(e.getMessage());
        }
        return account;
    }
}
