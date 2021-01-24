package fr.ing.interview.domain;

import fr.ing.interview.domain.exception.ForbiddenWithdrawOnNullBalanceException;
import fr.ing.interview.domain.exception.IllegalWithdrawAmountException;
import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;

public class Account {

    public static final double MINIMAL_DEPOSIT = 0.02;
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount >= MINIMAL_DEPOSIT) {
            balance = balance + amount;
            return true;
        } else {
            throw new InvalidAmountException("Invalid amount - Must be at least €" + MINIMAL_DEPOSIT);
        }
    }

    public boolean withdraw(double amount) {
        if (isaNegativeAmount(amount)){
            throw new IllegalWithdrawAmountException("Illegal amount for withdraw - Amount must be positive");
        }
        if (isNullBalance()) {
            throw new ForbiddenWithdrawOnNullBalanceException("Forbidden withdraw on account with null balance");
        }
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        } else {
            throw new NotAuthorizedOverdraftException("Not authorized overdraft - Maximum withdraw is €" + balance);
        }
    }

    private boolean isNullBalance() {
        return balance == 0;
    }

    private boolean isaNegativeAmount(double amount) {
        return amount < 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

}
