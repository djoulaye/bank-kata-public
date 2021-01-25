package fr.ing.interview.application;

import fr.ing.interview.application.exception.UnknownAccountException;
import fr.ing.interview.domain.Operation;
import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;
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
        accountService.depositMoney(accountNumber, amount);

    }

    public void withdrawMoneyFromAccount(String accountNumber, double amount) throws NotAuthorizedOverdraftException, UnknownAccountException {
        Operation operation = operationService.createWithdraw(amount);
        accountService.depositMoney(accountNumber, amount);
    }
}
