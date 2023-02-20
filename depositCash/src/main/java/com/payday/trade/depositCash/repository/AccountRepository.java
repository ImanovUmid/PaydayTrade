package com.payday.trade.depositCash.repository;

import com.payday.trade.depositCash.entity.Account;
import com.payday.trade.depositCash.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select t from Account t where t.userId = ?1")
    Optional<Account> findAccountByUserId(Users id);
}
