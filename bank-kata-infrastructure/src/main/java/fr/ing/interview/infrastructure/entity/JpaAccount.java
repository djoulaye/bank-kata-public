package fr.ing.interview.infrastructure.entity;

import fr.ing.interview.domain.Account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Account")
@Table(name = "ACCOUNT")
public class JpaAccount {

    @Id
    private String accountNumber;
    private double balance;

    public JpaAccount() {
    }

    public JpaAccount(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
    }

    public Account toAccount() {
        return new Account(accountNumber, balance);
    }
}
