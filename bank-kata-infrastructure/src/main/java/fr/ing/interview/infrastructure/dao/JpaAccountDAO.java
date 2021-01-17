package fr.ing.interview.infrastructure.dao;

import fr.ing.interview.infrastructure.entity.JpaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountDAO extends JpaRepository<JpaAccount, String> {
}
