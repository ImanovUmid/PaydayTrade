package com.payday.trade.emailNotification.repository;

import com.payday.trade.emailNotification.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select t from Users t where t.id = ?1")
    Users findUserByUserId(Long id);
}
