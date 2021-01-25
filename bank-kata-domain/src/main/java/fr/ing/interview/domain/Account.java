package fr.ing.interview.domain;

import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    public static final double MINIMAL_DEPOSIT = 0.02;
    private final String accountNumber;
    private double balance;
    private List<Operation> operations;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.operations = new ArrayList<>();
    }

    public Account(String accountNumber, double balance, List<Operation> operations) {
        this(accountNumber);
        this.balance = balance;
        this.operations = operations;
    }

    public boolean deposit(Operation operation) {
        if (operation.getAmount() >= MINIMAL_DEPOSIT) {
            balance = balance + operation.getAmount();
            operations.add(operation);
            return true;
        } else {
            throw new InvalidAmountException("Invalid amount - Must be at least €" + MINIMAL_DEPOSIT);
        }
    }

    public boolean withdraw(Operation operation) {
        if (balance >= operation.getAmount()) {
            balance = balance - operation.getAmount();
            operations.add(operation);
            return true;
        } else {
            //TODO mettre autre message si balance = 0
            throw new NotAuthorizedOverdraftException("Not authorized overdraft - Maximum withdraw is €" + balance);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return Collections.unmodifiableList(operations);
    }
}
