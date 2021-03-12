package com.bellagio.domain.wallet;

import com.bellagio.domain.Operation;
import com.bellagio.domain.OperationDirection;
import com.bellagio.domain.exception.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Tests on wallet entity")
class WalletTest {

    private static final String PLAYER_ID = "123456";
    public static final double AMOUNT_0_99 = 0.99;
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
        @DisplayName("Deposit maximum amount is 1500€")
        public void given_amount_above_1500_euro_then_refuse_deposit() {

        }

        @Test
        @DisplayName("Wallet maximum balance is 1000€")
        public void given_resulting_balance_above_10000_euro_then_refuse_deposit() {

        }

    }

    @Nested
    @DisplayName("Tests on wallet withdrawal")
    class WalletWithdrawalTest {

    }
}