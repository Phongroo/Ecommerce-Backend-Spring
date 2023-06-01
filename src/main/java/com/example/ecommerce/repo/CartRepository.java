package com.example.ecommerce.repo;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    public List<Cart> findByUser(User user);
    public Cart findByUserAndProduct(User user, Product product);
}
