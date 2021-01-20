package fr.ing.interview.controller;

import fr.ing.interview.application.AccountService;
import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.NotAuthorizedOverdraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity<Account> getAccount(@RequestParam String accountNumber) {
        return ResponseEntity.ok(accountService.getAmount(accountNumber));
    }

    @GetMapping("/create")
    public ResponseEntity<String> createAccount(@RequestParam String accountNumber) {
        accountService.createAccount(accountNumber);
        return ResponseEntity.ok("Account successfully created");
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam String accountNumber) {
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("Account successfully deleted");
    }

    @GetMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        accountService.depositMoney(accountNumber, amount);
        return ResponseEntity.ok("Account successfully credited");
    }

    @GetMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        try {
            accountService.withdrawMoney(accountNumber, amount);
        } catch (NotAuthorizedOverdraftException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Account successfully debited", HttpStatus.OK);
    }
}
