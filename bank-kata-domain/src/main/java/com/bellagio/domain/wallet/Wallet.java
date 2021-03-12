package com.bellagio.domain.wallet;

import com.bellagio.domain.Operation;
import com.bellagio.domain.exception.ExcessiveBalanceException;
import com.bellagio.domain.exception.InvalidAmountException;

public class Wallet {

    public static final double MINIMUM_AMOUNT_FOR_DEPOSIT = 1.00;
    public static final double MAXIMUM_AMOUNT_FOR_DEPOSIT = 1500.00;
    public static final double MAXIMUM_BALANCE_FOR_WALLET = 10000.00;
    private String playerId;
    private double balance;

    public Wallet(String playerId) {
        this.playerId = playerId;
    }

    public boolean deposit(Operation operation) {
        if (operation.getAmount() < MINIMUM_AMOUNT_FOR_DEPOSIT) {
            throw new InvalidAmountException("Deposit amount must be more than " + MINIMUM_AMOUNT_FOR_DEPOSIT + " euros");
        }

        if (operation.getAmount() > MAXIMUM_AMOUNT_FOR_DEPOSIT) {
            throw new InvalidAmountException("Deposit amount must be less than " + MAXIMUM_AMOUNT_FOR_DEPOSIT + " euros");
        }

        if (balance + operation.getAmount() > MAXIMUM_BALANCE_FOR_WALLET) {
            throw new ExcessiveBalanceException("Balance can't exceed  " + MAXIMUM_BALANCE_FOR_WALLET + " euros");
        }

        balance += operation.getAmount();
        return true;
    }
}
