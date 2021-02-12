package com.bellagio.application;

import com.bellagio.domain.Account;

public interface AccountInformation {

    double getBalanceOfAccount(String accountNumber);

    Account getHistoryOfAccount(String accountNumber);
}
