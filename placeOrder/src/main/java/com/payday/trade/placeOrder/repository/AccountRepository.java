package com.payday.trade.placeOrder.repository;

import com.payday.trade.placeOrder.entity.Account;
import com.payday.trade.placeOrder.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select t from Account t where t.accountType = '0' and t.userId = ?1")
    Optional<Account> findAccountByUserIdAndAccountType(Users id);

    @Query("select t from Account t where t.accountType = '1'  and t.userId = ?1 and t.tickerName =?2")
    Optional<Account> findAccountByUserIdAndTickerType(Users id, String tickerName);

}
