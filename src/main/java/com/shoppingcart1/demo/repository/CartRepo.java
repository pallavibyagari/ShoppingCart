package com.shoppingcart1.demo.repository;

import com.shoppingcart1.demo.model.Cart;
import com.shoppingcart1.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
   List<Cart>findAllByUserOrderByCreatedDateDesc(User user);
   List<Cart>deleteByUser(User user);
}
