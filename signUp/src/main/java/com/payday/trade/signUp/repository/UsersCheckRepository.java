package com.payday.trade.signUp.repository;

import com.payday.trade.signUp.entity.UsersCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersCheckRepository extends JpaRepository<UsersCheck, Long> {
    @Query("select t from UsersCheck t where t.sessionKey = ?1")
    Optional<UsersCheck> findUserCheckOptonalBySessionKey(String sessionKey);

}
