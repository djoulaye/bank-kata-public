package fr.ing.interview.domain;

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

    public boolean withdraw(double amount)  {
        if (balance >= amount) {
            balance = balance - amount;
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

}
