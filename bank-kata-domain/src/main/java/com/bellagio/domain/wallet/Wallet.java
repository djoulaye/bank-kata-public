package com.bellagio.domain.wallet;

import com.bellagio.domain.Operation;
import com.bellagio.domain.exception.InvalidAmountException;

public class Wallet {

    public static final double MINIMUM_AMOUNT_FOR_DEPOSIT = 1.00;
    public static final double MAXIMUM_AMOUNT_FOR_DEPOSIT = 1500.00;
    private String playerId;

    public Wallet(String playerId) {
        this.playerId = playerId;
    }

    public void deposit(Operation operation) {
        if (operation.getAmount() < MINIMUM_AMOUNT_FOR_DEPOSIT) {
            throw new InvalidAmountException("Deposit amount must be more than " + MINIMUM_AMOUNT_FOR_DEPOSIT + " euros");
        }

        if (operation.getAmount() > MAXIMUM_AMOUNT_FOR_DEPOSIT) {
            throw new InvalidAmountException("Deposit amount must be less than " + MAXIMUM_AMOUNT_FOR_DEPOSIT + " euros");
        }
    }
}
