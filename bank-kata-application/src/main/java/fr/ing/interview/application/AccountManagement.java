package fr.ing.interview.application;

import fr.ing.interview.domain.Account;

public interface AccountManagement {

    void createAccount(String accountNumber);

    void deleteAccount(String accountNumber);

    void updateAccount(Account account);

}
