package com.payday.trade.signUp.repository;

import com.payday.trade.signUp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select t from Users t where t.username = ?1")
    Optional<Users> findUserByUserOptionalName(String userName);

    @Query("select t from Users t where t.username = ?1")
    Users findUserByUserName(String userName);
}
