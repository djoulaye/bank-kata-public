package fr.ing.interview.domain;

import java.util.List;

public interface OperationRepository {

    List<Operation> listByAccountNumber(String accountNumber);

    void save(Operation operation);
}
