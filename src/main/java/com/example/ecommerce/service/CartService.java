package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;

import java.util.List;

public interface CartService {
    public Cart addCart(Cart cartt, Long productId);
    List<Cart> listCart();
    public void deleteCart(Long cardId);
    public Cart updateCate( Long productId,Double quantily);
}
