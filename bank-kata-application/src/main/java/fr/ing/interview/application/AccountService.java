package fr.ing.interview.application;

import fr.ing.interview.application.exception.AlreadyExistsAccountException;
import fr.ing.interview.application.exception.UnknownAccountException;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
import fr.ing.interview.domain.Operation;
import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> listAccounts() {
        return Collections.unmodifiableList(accountRepository.listAll());
    }

    public void depositMoney(String accountNumber, Operation operation) throws InvalidAmountException {
        Account account = getAccount(accountNumber);
        account.deposit(operation);
        updateAccount(account);
    }

    public void withdrawMoney(String accountNumber, Operation operation) throws NotAuthorizedOverdraftException, UnknownAccountException {
        Account account;
        account = getAccount(accountNumber);
        account.withdraw(operation);
        updateAccount(account);
    }

    public Account getAccount(String accountNumber) throws UnknownAccountException {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new UnknownAccountException("Account " + accountNumber + " doesn't exist");
        }
    }

    public void createAccount(String accountNumber) throws AlreadyExistsAccountException {
        if (!isExist(accountNumber)) {
            accountRepository.save(new Account(accountNumber));
        } else {
            throw new AlreadyExistsAccountException("Account " + accountNumber + " already exists");
        }
    }

    public void deleteAccount(String accountNumber) {
        if (isExist(accountNumber)) {
            accountRepository.delete(accountNumber);
        } else {
            throw new UnknownAccountException("Account " + accountNumber + " doesn't exist");
        }
    }

    private void updateAccount(Account account) {
        accountRepository.save(account);
    }

    private boolean isExist(String accountNumber) {
        return accountRepository.isExists(accountNumber);
    }

}
