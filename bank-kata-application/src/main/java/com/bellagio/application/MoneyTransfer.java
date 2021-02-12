package com.bellagio.application;

public interface MoneyTransfer {

    void depositMoneyToAccount(String accountNumber, double amount);

    void withdrawMoneyFromAccount(String accountNumber, double amount);
}
