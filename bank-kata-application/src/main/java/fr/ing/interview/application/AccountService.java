package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
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
        Account account = displayBalance(accountNumber);
        account.deposit(amount);
        updateAccount(account);
    }

    public void withdrawMoney(String accountNumber, double amount) {
        Account account = displayBalance(accountNumber);
        account.withdraw(amount);
        updateAccount(account);
    }

    public Account displayBalance(String accountNumber) {
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
