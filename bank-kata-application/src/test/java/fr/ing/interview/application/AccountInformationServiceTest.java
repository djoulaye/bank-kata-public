package fr.ing.interview.application;

import fr.ing.interview.domain.Account;
import fr.ing.interview.domain.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountInformationServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountInformationService accountInformationService;

    //    @Spy
//    @InjectMocks
//    private AccountService accountService = new AccountService();
    @InjectMocks
    private AccountService accountService = Mockito.spy(new AccountService());

    private static final String EXISTING_ACCOUNT_NUMBER = "123";

    @Test
//    @Disabled("Ne fonctionne pas")
    void get_balance_on_existing_account_should_not_throw_exception() {
        //Arrange
        Account account = new Account(EXISTING_ACCOUNT_NUMBER);
//        AccountService accountService = new AccountService();
        when(accountRepository.findByAccountNumber(EXISTING_ACCOUNT_NUMBER)).thenReturn(Optional.of(account));
//        when(accountService.getAccount(EXISTING_ACCOUNT_NUMBER)).thenReturn(account);

        //Act & Assert
        assertThatCode(() -> accountInformationService.getBalanceOfAccount(EXISTING_ACCOUNT_NUMBER)).doesNotThrowAnyException();
    }
}