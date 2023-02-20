package com.payday.trade.signIn.repository;

import com.payday.trade.signIn.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select t from Users t where t.username = ?1 and t.password = ?2")
    Optional<Users> findUserByUserNameAndPassword(String userName, String password);
}
