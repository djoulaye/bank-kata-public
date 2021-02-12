package com.bellagio.domain;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class HistorySteps {

    private Account account;

    @Given("^that user's account number is (.*)$")
    public void createAccount(String accountNumber) throws Throwable {
        account = new Account(accountNumber);
    }

    @When("^the user makes a deposit of (.*)")
    public void deposit(float amount) {
        Operation operation = new Operation(OperationDirection.CREDIT, amount);
        account.deposit(operation);
    }

    @When("^the user makes a withdrawal of (.*)$")
    public void withdraw(float amount) {
        Operation operation = new Operation(OperationDirection.DEBIT, amount);
        account.withdraw(operation);
    }

    @Then("account (.*) has a balance of (.*)$")
    public void verifyBalance(String accountNumber, float balance) {
        assertThat(account.getAccountNumber()).isEqualTo(accountNumber);
        assertThat(account.getBalance()).isEqualTo(balance);
    }

    @Then("account has (.*) operations in its history$")
    public void verifyHistory(int numberOfOperations) {
        assertThat(account.getOperations()).size().isEqualTo(numberOfOperations);
    }
}
