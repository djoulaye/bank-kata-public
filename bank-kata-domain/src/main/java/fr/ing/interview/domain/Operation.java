package fr.ing.interview.domain;

import java.util.Date;

public class Operation {

    private final Date operationDate;
    private final OperationDirection direction;
    private final double amount;

    public Operation(OperationDirection direction, double amount) {
        this.operationDate = new Date();
        this.direction = direction;
        this.amount = amount;
    }

    public Operation(OperationDirection direction, double amount, Date operationDate) {
        this.operationDate = operationDate;
        this.direction = direction;
        this.amount = amount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public OperationDirection getDirection() {
        return direction;
    }

    public double getAmount() {
        return amount;
    }
}
