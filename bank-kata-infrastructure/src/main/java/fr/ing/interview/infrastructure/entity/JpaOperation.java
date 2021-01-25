package fr.ing.interview.infrastructure.entity;

import fr.ing.interview.domain.Operation;
import fr.ing.interview.domain.OperationDirection;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Operation")
@Table(name = "OPERATION")
public class JpaOperation {

    @Id
    @Generated(GenerationTime.INSERT)
    private Long operationId;

    @ManyToOne
    private JpaAccount account;

    private Date operationDate;
    @Enumerated(EnumType.STRING)
    private OperationDirection direction;
    private double amount;

    public JpaOperation() {
    }

    public JpaOperation(Operation operation) {
        this.operationDate = operation.getOperationDate();
        this.direction = operation.getDirection();
        this.amount = operation.getAmount();
    }

    public Operation toOperation() {
        return new Operation(direction, amount, operationDate);
    }

}
