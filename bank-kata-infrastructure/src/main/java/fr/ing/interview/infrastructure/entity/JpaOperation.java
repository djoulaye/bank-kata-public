package fr.ing.interview.infrastructure.entity;

import fr.ing.interview.domain.Operation;
import fr.ing.interview.domain.OperationDirection;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Operation")
@Table(name = "OPERATION")
public class JpaOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long operationId;

    private Date operationDate;
    @Enumerated(EnumType.STRING)
    private OperationDirection direction;
    private double amount;

    public JpaOperation() {
    }

    public JpaOperation(Operation operation) {
        this.operationId = Objects.isNull(operation.getOperationId()) ? 0 : operation.getOperationId();
        this.operationDate = operation.getOperationDate();
        this.direction = operation.getDirection();
        this.amount = operation.getAmount();
    }

    public Operation toOperation() {
        return new Operation(operationId, direction, amount, operationDate);
    }

}
