package com.shoppingcart1.demo.repository;

import com.shoppingcart1.demo.model.Cart_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_CategoryRepo extends JpaRepository<Cart_Category,Integer> {
}
