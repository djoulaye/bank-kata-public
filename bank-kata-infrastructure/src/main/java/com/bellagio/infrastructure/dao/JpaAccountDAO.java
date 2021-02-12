package com.bellagio.infrastructure.dao;

import com.bellagio.infrastructure.entity.JpaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountDAO extends JpaRepository<JpaAccount, String> {
}
