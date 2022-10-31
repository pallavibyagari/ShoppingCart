package com.shoppingcart1.demo.repository;

import com.shoppingcart1.demo.model.Order;
import com.shoppingcart1.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order>findAllByUserOrderByCreatedDateDesc(User user);
}
