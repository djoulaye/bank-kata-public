package com.bellagio.domain.wallet;

import com.bellagio.domain.Operation;
import com.bellagio.domain.OperationDirection;
import com.bellagio.domain.exception.ExcessiveBalanceException;
import com.bellagio.domain.exception.InvalidAmountException;
import com.bellagio.domain.exception.TooManyDepositException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Tests on wallet entity")
class WalletTest {

    private static final String PLAYER_ID = "123456";
    public static final double AMOUNT_0_99 = 0.99;
    public static final double AMOUNT_1_00 = 1.00;
    public static final double AMOUNT_1500_01 = 1500.01;
    public static final double AMOUNT_500_00 = 500.00;
    public static final double AMOUNT_1000_00 = 1000.00;
    private Wallet wallet;

    @BeforeEach
    private void init() {
        wallet = new Wallet(PLAYER_ID);
    }

    @Nested
    @DisplayName("Tests on wallet deposit")
    class WalletDepositTest {

        @Test
        @DisplayName("Deposit minimum amount is 1€")
        public void given_amount_under_1_euro_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_0_99);
            assertThatThrownBy(() -> wallet.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @Test
        @DisplayName("Deposit maximum amount is 1,500€")
        public void given_amount_above_1500_euro_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_1500_01);
            assertThatThrownBy(() -> wallet.deposit(operation)).isInstanceOf(InvalidAmountException.class);
        }

        @Test
        @DisplayName("Deposit between limits is accepted")
        public void given_amount_between_limits_then_accept_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_500_00);
            assertThat(wallet.deposit(operation)).isTrue();
        }

        @Test
        @DisplayName("Wallet maximum balance is 10,000€")
        public void given_resulting_balance_above_10000_euro_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_1000_00);
            for (int i = 0; i < 10; i++) {
                assertThat(wallet.deposit(operation)).isTrue();
            }
            Operation operation2 = new Operation(OperationDirection.CREDIT, AMOUNT_1_00);
            assertThatThrownBy(() -> wallet.deposit(operation2)).isInstanceOf(ExcessiveBalanceException.class);
        }

        @Test
        @DisplayName("Only one deposit per day is allowed")
        public void given_more_than_one_deposit_on_same_day_then_refuse_deposit() {
            Operation operation = new Operation(OperationDirection.CREDIT, AMOUNT_1000_00);
            assertThat(wallet.deposit(operation)).isTrue();
            assertThatThrownBy(() -> wallet.deposit(operation)).isInstanceOf(TooManyDepositException.class);
        }

    }

    @Nested
    @DisplayName("Tests on wallet withdrawal")
    class WalletWithdrawalTest {

    }
}