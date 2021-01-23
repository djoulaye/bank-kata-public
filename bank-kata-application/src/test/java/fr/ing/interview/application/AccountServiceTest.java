package fr.ing.interview.application;

import fr.ing.interview.domain.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private static final String EXISTING_ACCOUNT_NUMBER = "123";
    private static final String NOT_EXISTING_ACCOUNT_NUMBER = "124";

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void deposit_amount_on_not_existing_account_should_throw_exception() {
        assertThatThrownBy(() -> accountService.depositMoney(NOT_EXISTING_ACCOUNT_NUMBER, 2)).isInstanceOf(UnknownAccountException.class);
    }

    @Test
    void delete_not_existing_account_should_throw_exception() {
        when(accountRepository.isExists(NOT_EXISTING_ACCOUNT_NUMBER)).thenReturn(false);
        assertThatThrownBy(() -> accountService.deleteAccount(NOT_EXISTING_ACCOUNT_NUMBER)).isInstanceOf(UnknownAccountException.class);
        verify(accountRepository, times(1)).isExists(NOT_EXISTING_ACCOUNT_NUMBER);
    }

    @Test
    public void create_already_existing_account_should_throws_exception() {
        when(accountRepository.isExists(EXISTING_ACCOUNT_NUMBER)).thenReturn(true);
        assertThat(accountRepository.isExists(EXISTING_ACCOUNT_NUMBER)).isTrue();
        assertThatThrownBy(() -> accountService.createAccount(EXISTING_ACCOUNT_NUMBER)).isInstanceOf(AlreadyExistsAccountException.class);
    }
}