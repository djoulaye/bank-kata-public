package fr.ing.interview.domain;


import fr.ing.interview.domain.exception.InvalidAmountException;
import fr.ing.interview.domain.exception.NotAuthorizedOverdraftException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tests on account entity")
class AccountTest {

    private static final String ACCOUNT_NUMBER = "123456";
    private Account account;

    @BeforeEach
    private void init() {
        account = new Account(ACCOUNT_NUMBER);
    }

    @Nested
    @DisplayName("Tests on deposit function")
    class DepositTest {
        @Test
        @DisplayName("€0.01 deposit must be refused")
        void given_amount_of_0_01_when_deposit_on_existing_account_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, 0.01);
            assertThatThrownBy(() -> account.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @Test
        @DisplayName("Minimum deposit must be accepted")
        void given_amount_of_0_02_when_deposit_on_existing_account_then_accept_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, 0.02);
            assertThat(account.deposit(operation)).isTrue();
        }

        @Test
        @DisplayName("Negative deposit must be refused")
        void given_negative_amount_when_deposit_on_existing_account_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, -0.01);
            assertThatThrownBy(() -> account.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @ParameterizedTest(name = "€{0} deposit must be accepted")
        @ValueSource(doubles = {15.00, 150.50, 1000.99})
        @DisplayName("Deposit superior of minimum must be accepted")
        void given_amount_superior_of_0_02_when_deposit_on_existing_account_then_accept_deposit(Double amount) {
            Operation operation = new Operation(OperationDirection.CREDIT, amount);
            assertThat(account.deposit(operation)).isTrue();
        }
    }

    @Nested
    @DisplayName("Tests on withdraw function")
    class WithdrawTest {
        @Test
        @DisplayName("Withdraw inferior to balance must be accepted")
        void given_amount_inferior_to_balance_when_withdraw_on_existing_account_then_accept_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, 10.00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, 5.00);
            account.deposit(deposit);
            assertThat(account.withdraw(withdrawal)).isTrue();
        }

        @Test
        @DisplayName("Withdraw equals to balance must be accepted")
        void given_amount_equals_to_balance_when_withdraw_on_existing_account_then_accept_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, 10.00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, 10.00);
            account.deposit(deposit);
            assertThat(account.withdraw(withdrawal)).isTrue();
        }

        @Test
        @DisplayName("Withdraw superior to balance must be refused")
        void given_amount_superior_to_balance_when_withdraw_on_existing_account_then_refuse_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, 10.00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, 15.00);
            account.deposit(deposit);
            assertThatThrownBy(() -> account.withdraw(withdrawal)).isInstanceOf(NotAuthorizedOverdraftException.class);
        }
    }
}
