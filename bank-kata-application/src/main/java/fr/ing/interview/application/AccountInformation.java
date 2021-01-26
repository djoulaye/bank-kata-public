package fr.ing.interview.application;

import fr.ing.interview.domain.Account;

public interface AccountInformation {

    double getBalanceOfAccount(String accountNumber);

    Account getHistoryOfAccount(String accountNumber);
}
