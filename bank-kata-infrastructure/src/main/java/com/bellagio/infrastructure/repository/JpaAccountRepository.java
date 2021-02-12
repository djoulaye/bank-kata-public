package com.bellagio.infrastructure.repository;

import com.bellagio.domain.Account;
import com.bellagio.domain.AccountRepository;
import com.bellagio.infrastructure.dao.JpaAccountDAO;
import com.bellagio.infrastructure.entity.JpaAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaAccountRepository implements AccountRepository {

    @Autowired
    private JpaAccountDAO jpaAccountDAO;

    @Override
    public List<Account> listAll() {
        return jpaAccountDAO.findAll()
                .stream()
                .map(JpaAccount::toAccount)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return jpaAccountDAO.findById(accountNumber)
                .map(JpaAccount::toAccount);
    }

    @Override
    public void save(Account account) {
        jpaAccountDAO.save(new JpaAccount(account));
    }

    @Override
    public void delete(String accountNumber) {
        jpaAccountDAO.deleteById(accountNumber);
    }

    @Override
    public boolean isExists(String accountNumber) {
        return jpaAccountDAO.existsById(accountNumber);
    }
}
