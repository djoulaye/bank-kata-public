package fr.ing.interview.exposition.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountHistoryDto {
    private String accountNumber;
    private double balance;
    private List<OperationDto> operations = new ArrayList<>();

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }
}
