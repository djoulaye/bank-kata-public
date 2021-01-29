package fr.ing.interview.exposition.controller;

import fr.ing.interview.application.AccountInformation;
import fr.ing.interview.application.AccountManagement;
import fr.ing.interview.application.MoneyTransfer;
import fr.ing.interview.application.exception.AlreadyExistsAccountException;
import fr.ing.interview.application.exception.UnknownAccountException;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;
import fr.ing.interview.exposition.adapter.AccountHistoryAdapter;
import fr.ing.interview.exposition.dto.AccountHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountInformation accountInformation;

    @Autowired
    private AccountManagement accountManagement;

    @Autowired
    private MoneyTransfer moneyTransfer;

    @Autowired
    private AccountHistoryAdapter accountHistoryAdapter;

    @GetMapping("/displayBalance/{accountNumber}")
    public ResponseEntity<String> displayBalance(@PathVariable("accountNumber") String accountNumber) {
        double balance;
        try {
            balance = accountInformation.getBalanceOfAccount(accountNumber);
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(String.valueOf(balance), HttpStatus.OK);
    }

    @GetMapping("/displayHistory/{accountNumber}")
    public ResponseEntity<AccountHistoryDto> displayHistory(@PathVariable("accountNumber") String accountNumber) {
        Account account;
        try {
            account = accountInformation.getHistoryOfAccount(accountNumber);
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountHistoryAdapter.convert(account), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestParam String accountNumber) {
        try {
            accountManagement.createAccount(accountNumber);
        } catch (AlreadyExistsAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully created", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String accountNumber) {
        try {
            accountManagement.deleteAccount(accountNumber);
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            moneyTransfer.depositMoneyToAccount(accountNumber, amount);
        } catch (InvalidAmountException | UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully credited", HttpStatus.OK);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            moneyTransfer.withdrawMoneyFromAccount(accountNumber, amount);
        } catch (NotAuthorizedOverdraftException | UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully debited", HttpStatus.OK);
    }
}
