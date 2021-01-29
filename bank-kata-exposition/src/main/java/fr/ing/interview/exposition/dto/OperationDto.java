package fr.ing.interview.exposition.dto;

import fr.ing.interview.domain.OperationDirection;

import java.util.Date;

public class OperationDto {
    private Date date;
    private OperationDirection direction;
    private double amount;

    public Date getDate() {
        return date;
    }

    public OperationDirection getDirection() {
        return direction;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDirection(OperationDirection direction) {
        this.direction = direction;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
