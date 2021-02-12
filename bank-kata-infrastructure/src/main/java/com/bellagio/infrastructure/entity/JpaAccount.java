package com.bellagio.infrastructure.entity;

import com.bellagio.domain.Account;
import com.bellagio.domain.Operation;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Account")
@Table(name = "ACCOUNT")
public class JpaAccount {

    @Id
    private String accountNumber;
    private double balance;
    @OneToMany(cascade = CascadeType.ALL)
    private List<JpaOperation> operations;

    public JpaAccount() {
    }

    public JpaAccount(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
        this.operations = account.getOperations()
                .stream()
                .map(operation -> new JpaOperation(operation))
                .collect(Collectors.toList());
    }

    public Account toAccount() {
        List<Operation> operations = this.operations.stream().map(JpaOperation::toOperation).collect(Collectors.toList());
        return new Account(accountNumber, balance, operations);
    }
}
