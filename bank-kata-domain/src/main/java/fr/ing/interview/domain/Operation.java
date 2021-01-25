package fr.ing.interview.domain;

import java.util.Date;

public class Operation {

    private final Long operationId;
    private final Date operationDate;
    private final OperationDirection direction;
    private final double amount;

    public Operation(OperationDirection direction, double amount) {
        this.operationId = null;
        this.operationDate = new Date();
        this.direction = direction;
        this.amount = amount;
    }

    public Operation(long operationId, OperationDirection direction, double amount, Date operationDate) {
        this.operationId = operationId;
        this.operationDate = operationDate;
        this.direction = direction;
        this.amount = amount;
    }

    public Long getOperationId() {
        return operationId;
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
