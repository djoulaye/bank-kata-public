package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
import fr.ing.interview.domain.InvalidAmountException;
import fr.ing.interview.domain.NotAuthorizedOverdraftException;
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

    public void depositMoney(String accountNumber, double amount) {
        Account account = getAmount(accountNumber);
        try {
            account.deposit(amount);
        } catch (InvalidAmountException e){
            throw new InvalidAmountException(e.getMessage());
        }
        updateAccount(account);
    }

    public void withdrawMoney(String accountNumber, double amount) {
        Account account;
        try {
            account = getAmount(accountNumber);
        } catch (UnknownAccountException e) {
            throw new UnknownAccountException(e.getMessage());
        }
        try {
            account.withdraw(amount);
        } catch (NotAuthorizedOverdraftException e) {
            throw new NotAuthorizedOverdraftException(e.getMessage());
        }
        updateAccount(account);
    }

    public Account getAmount(String accountNumber) {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new UnknownAccountException("Account " + accountNumber + " doesn't exist");
        }
    }

    public void createAccount(String accountNumber) {
        accountRepository.save(new Account(accountNumber));
    }

    public void deleteAccount(String accountNumber) {
        accountRepository.delete(accountNumber);
    }

    private void updateAccount(Account account) {
        accountRepository.save(account);
    }

}
