package com.bellagio.domain;


import com.bellagio.domain.exception.ForbiddenWithdrawOnNullBalanceException;
import com.bellagio.domain.exception.IllegalWithdrawAmountException;
import com.bellagio.domain.exception.InvalidAmountException;
import com.bellagio.domain.exception.NotAuthorizedOverdraftException;
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
    public static final double AMOUNT_10_00 = 10.00;
    public static final double AMOUNT_15_00 = 15.00;
    public static final double AMOUNT_5_00 = 5.00;
    public static final double AMOUNT_150_50 = 150.50;
    public static final double AMOUNT_1000_99 = 1000.99;
    public static final double AMOUNT_0_01 = 0.01;
    public static final double NEGATIVE_AMOUNT_0_01 = -AMOUNT_0_01;
    public static final double AMOUNT_0_02 = 0.02;
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
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_0_01);
            assertThatThrownBy(() -> account.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @Test
        @DisplayName("Minimum deposit must be accepted")
        void given_amount_of_0_02_when_deposit_on_existing_account_then_accept_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_0_02);
            assertThat(account.deposit(operation)).isTrue();
        }

        @Test
        @DisplayName("Negative deposit must be refused")
        void given_negative_amount_when_deposit_on_existing_account_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, NEGATIVE_AMOUNT_0_01);
            assertThatThrownBy(() -> account.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @ParameterizedTest(name = "€{0} deposit must be accepted")
        @ValueSource(doubles = {AMOUNT_15_00, AMOUNT_150_50, AMOUNT_1000_99})
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
            Operation deposit = new Operation(OperationDirection.CREDIT, AMOUNT_10_00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, AMOUNT_5_00);
            account.deposit(deposit);
            assertThat(account.withdraw(withdrawal)).isTrue();
        }

        @Test
        @DisplayName("Withdraw equals to balance must be accepted")
        void given_amount_equals_to_balance_when_withdraw_on_existing_account_then_accept_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, AMOUNT_10_00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, AMOUNT_10_00);
            account.deposit(deposit);
            assertThat(account.withdraw(withdrawal)).isTrue();
        }

        @Test
        @DisplayName("Withdraw superior to balance must be refused")
        void given_amount_superior_to_balance_when_withdraw_on_existing_account_then_refuse_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, AMOUNT_10_00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, AMOUNT_15_00);
            account.deposit(deposit);
            assertThatThrownBy(() -> account.withdraw(withdrawal)).isInstanceOf(NotAuthorizedOverdraftException.class);
        }

        @Test
        @DisplayName("Withdraw negative amount must be refused")
        void given_negative_amount_when_withdraw_on_existing_account_then_refuse_withdraw() {
            Operation deposit = new Operation(OperationDirection.CREDIT, AMOUNT_10_00);
            Operation withdrawal = new Operation(OperationDirection.DEBIT, NEGATIVE_AMOUNT_0_01);
            assertThatThrownBy(() -> account.withdraw(withdrawal)).isInstanceOf(IllegalWithdrawAmountException.class);
        }

        @Test
        @DisplayName("Withdraw is forbidden when balance is null")
        void given_account_with_null_balance_when_withdraw_on_existing_account_then_refuse_withdraw() {
            Operation withdrawal = new Operation(OperationDirection.DEBIT, AMOUNT_10_00);
            assertThatThrownBy(() -> account.withdraw(withdrawal)).isInstanceOf(ForbiddenWithdrawOnNullBalanceException.class);
        }
    }
}
