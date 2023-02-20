package com.payday.trade.placeOrder.repository;

import com.payday.trade.placeOrder.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select t from Orders t where t.id = ?1")
    Optional<Orders> findOrderById(Long id);

}
