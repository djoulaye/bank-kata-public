package com.bellagio.domain;

import java.util.List;

public interface OperationRepository {

    List<Operation> listByAccountNumber(String accountNumber);

    void save(Operation operation);
}
