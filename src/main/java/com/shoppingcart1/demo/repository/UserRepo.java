package com.shoppingcart1.demo.repository;

import com.shoppingcart1.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
  User findByEmail(String email);
}
