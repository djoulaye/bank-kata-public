package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
import fr.ing.interview.domain.InvalidAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {


    public static final String NOT_EXISTING_ACCOUNT_NUMBER = "123";


    @Test
    @Disabled
    void deposit_amount_on_not_existing_account_must_throw_exception(){
        AccountService accountService = new AccountService();
        //TODO ajouter un mock de AccountRepository pour finaliser le test
        assertThatThrownBy(() -> accountService.depositMoney(NOT_EXISTING_ACCOUNT_NUMBER,2)).isInstanceOf(UnknownAccountException.class);
    }
}