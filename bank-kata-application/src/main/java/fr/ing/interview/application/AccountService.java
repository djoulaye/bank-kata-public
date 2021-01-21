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

    public void depositMoney(String accountNumber, double amount) throws InvalidAmountException {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        updateAccount(account);
    }

    public void withdrawMoney(String accountNumber, double amount) throws NotAuthorizedOverdraftException, UnknownAccountException {
        Account account;
        account = getAccount(accountNumber);
        account.withdraw(amount);
        updateAccount(account);
    }

    public Account getAccount(String accountNumber) throws UnknownAccountException{
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new UnknownAccountException("Account " + accountNumber + " doesn't exist");
        }
    }

    public double getBalance(String accountNumber) throws  UnknownAccountException{
        double balance;
        //TODO ne devrait pas être nécessaire mais l'exception ne se propage pas sinon :/
        try {
            balance = getAccount(accountNumber).getBalance();
        } catch (UnknownAccountException e){
            throw new UnknownAccountException(e.getMessage());
        }
        return balance;
    }

    public void createAccount(String accountNumber) throws AlreadyExistsAccountException{
        if (!isExist(accountNumber)){
            accountRepository.save(new Account(accountNumber));
        }
        else {
            throw new AlreadyExistsAccountException ("Account " + accountNumber + " already exists");
        }
    }

    public void deleteAccount(String accountNumber) {
        if (isExist(accountNumber)){
            accountRepository.delete(accountNumber);
        }
        else {
            throw new UnknownAccountException("Account " + accountNumber + " doesn't exist");
        }
    }

    private void updateAccount(Account account) {
        accountRepository.save(account);
    }

    private boolean isExist(String accountNumber){
        return accountRepository.isExists(accountNumber);
    }

}
