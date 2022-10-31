package com.shoppingcart1.demo.repository;

import com.shoppingcart1.demo.model.Cart_Category;
import com.shoppingcart1.demo.model.Cart_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface Cart_ProductRepo extends JpaRepository<Cart_Product,Integer> {
    }

