package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
import fr.ing.interview.domain.InvalidAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    public static final String EXISTING_ACCOUNT_NUMBER = "123";
    @Mock
    AccountRepository accountRepository;

    public static final String NOT_EXISTING_ACCOUNT_NUMBER = "124";


    @Test
    void deposit_amount_on_not_existing_account_must_throw_exception() {
        AccountService accountService = new AccountService();
        when(accountRepository.isExists(NOT_EXISTING_ACCOUNT_NUMBER)).thenReturn(false);
        assertThatThrownBy(() -> accountService.depositMoney(NOT_EXISTING_ACCOUNT_NUMBER, 2)).isInstanceOf(UnknownAccountException.class);
        verify(accountRepository, times(1)).isExists(NOT_EXISTING_ACCOUNT_NUMBER);
    }

    @Test
    @Disabled
    public void create_already_existing_account_should_throws_exception() {
        AccountService accountService = new AccountService();
        when(accountRepository.isExists(EXISTING_ACCOUNT_NUMBER)).thenReturn(true);
        assertThat(accountRepository.isExists(EXISTING_ACCOUNT_NUMBER)).isTrue();
        accountService.createAccount(EXISTING_ACCOUNT_NUMBER);
        //verify(accountRepository,times(1)).save(any(Account.class));

    }
}