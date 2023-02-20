package com.payday.trade.depositCash.repository;

import com.payday.trade.depositCash.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select t from Users t where t.id = ?1")
    Optional<Users> findUserByUserOptionalId(Long id);
}
