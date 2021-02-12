package com.bellagio.application;

import com.bellagio.application.exception.UnknownAccountException;
import com.bellagio.domain.Operation;
import com.bellagio.domain.exception.InvalidAmountException;
import com.bellagio.domain.exception.NotAuthorizedOverdraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoneyTransferService implements MoneyTransfer {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    public void depositMoneyToAccount(String accountNumber, double amount) throws InvalidAmountException, UnknownAccountException {
        Operation operation = operationService.createDeposit(amount);
        accountService.depositMoney(accountNumber, operation);

    }

    public void withdrawMoneyFromAccount(String accountNumber, double amount) throws NotAuthorizedOverdraftException, UnknownAccountException {
        Operation operation = operationService.createWithdraw(amount);
        accountService.withdrawMoney(accountNumber, operation);
    }
}
