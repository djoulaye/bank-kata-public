package com.bellagio.domain.wallet;

import com.bellagio.domain.Operation;
import com.bellagio.domain.exception.ExcessiveBalanceException;
import com.bellagio.domain.exception.InvalidAmountException;
import com.bellagio.domain.exception.TooManyDepositException;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Wallet {

    public static final double MINIMUM_AMOUNT_FOR_DEPOSIT = 1.00;
    public static final double MAXIMUM_AMOUNT_FOR_DEPOSIT = 1500.00;
    public static final double MAXIMUM_BALANCE_FOR_WALLET = 10000.00;
    private String playerId;
    private double balance;
    private Date lastDepositDate;

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

        if (isDateOfToday(lastDepositDate)) {
            throw new TooManyDepositException("Only one deposit is allowed each day");
        }

        balance += operation.getAmount();
        lastDepositDate = new Date();

        return true;
    }

    private boolean isDateOfToday(Date lastDepositDate) {
        if (Objects.isNull(lastDepositDate)) {
            return false;
        }

        Calendar calLastDeposit = Calendar.getInstance();
        calLastDeposit.setTime(lastDepositDate);

        Calendar calCurrentDeposit = Calendar.getInstance();
        calCurrentDeposit.setTime(new Date());

        boolean sameDay = calLastDeposit.get(Calendar.DAY_OF_YEAR) == calCurrentDeposit.get(Calendar.DAY_OF_YEAR) &&
                calLastDeposit.get(Calendar.YEAR) == calCurrentDeposit.get(Calendar.YEAR);
        return sameDay;
    }
}
