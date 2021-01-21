package fr.ing.interview.domain;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> listAll();

    Optional<Account> findByAccountNumber(String accountNumber);

    void save(Account account);

    void delete(String accountNumber);

    boolean isExists(String accountNumber);
}
