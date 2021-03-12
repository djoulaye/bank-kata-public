package com.bellagio.domain.wallet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Tests on wallet entity")
class WalletTest {

    @Nested
    @DisplayName("Tests on wallet deposit")
    class WalletDepositTest {

        @Test
        @DisplayName("Deposit minimum amount is 1€")
        public void given_amount_under_1_euro_then_refuse_deposit() {

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