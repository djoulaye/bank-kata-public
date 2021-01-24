package fr.ing.interview.controller;

import fr.ing.interview.application.AccountService;
import fr.ing.interview.application.exception.AlreadyExistsAccountException;
import fr.ing.interview.application.exception.UnknownAccountException;
import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/displayBalance/{accountNumber}")
    public ResponseEntity<String> displayBalance(@PathVariable("accountNumber") String accountNumber) {
        double balance;
        try {
            balance = accountService.getBalance(accountNumber);
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(String.valueOf(balance), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestParam String accountNumber) {
        try {
            accountService.createAccount(accountNumber);
        } catch (AlreadyExistsAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully created", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String accountNumber) {
        try {
            accountService.deleteAccount(accountNumber);
        } catch (UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            accountService.depositMoney(accountNumber, amount);
        } catch (InvalidAmountException | UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully credited", HttpStatus.OK);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            accountService.withdrawMoney(accountNumber, amount);
        } catch (NotAuthorizedOverdraftException | UnknownAccountException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully debited", HttpStatus.OK);
    }
}
