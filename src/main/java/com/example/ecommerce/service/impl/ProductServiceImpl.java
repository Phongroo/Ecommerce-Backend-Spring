package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.JwtAuthenticationFilter;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repo.CartRepository;
import com.example.ecommerce.repo.ProductRepository;
import com.example.ecommerce.repo.UserRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {
    this.productRepository.deleteById(productId);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Cart> getProductDetail(boolean isSingleProductCheckout) {

            String username= JwtAuthenticationFilter.CURRENT_USER;
            User user= userRepository.findByUsername(username);
           List<Cart> carts=cartRepository.findByUser(user);
            return carts;


    }
}
