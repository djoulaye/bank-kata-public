package com.bellagio.application;

import com.bellagio.domain.Operation;
import com.bellagio.domain.OperationDirection;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

//    @Autowired
//    private OperationRepository operationRepository;

    public Operation createDeposit(double amount) {
        Operation operation = new Operation(OperationDirection.CREDIT, amount);
//        operationRepository.save(operation);
        return operation;
    }

    public Operation createWithdraw(double amount) {
        Operation operation = new Operation(OperationDirection.DEBIT, amount);
//        operationRepository.save(operation);
        return operation;
    }
}
