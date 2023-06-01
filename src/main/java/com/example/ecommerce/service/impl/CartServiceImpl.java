package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.JwtAuthenticationFilter;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repo.CartRepository;
import com.example.ecommerce.repo.ProductRepository;
import com.example.ecommerce.repo.UserRepository;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Cart addCart(Cart cartt, Long productId) {
        String username= JwtAuthenticationFilter.CURRENT_USER;
        Product product=productRepository.findById(productId).get();
        User user=null;
        if (username!=null){
            user= userRepository.findByUsername(username);
        }

        List<Cart> cartList = cartRepository.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if(filteredList.size() > 0) {
            Cart cartr=cartRepository.findByUserAndProduct(user,product);
            cartr.setQuantily(cartr.getQuantily()+cartt.getQuantily());
            cartr.setUser(user);
            cartr.setProduct(product);
            return cartRepository.save(cartr);
        }

        if(product!=null&&user!=null){
            Cart cart=new Cart(
                    cartt.getQuantily(),
                    product,user);
          return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public List<Cart> listCart() {
        String username= JwtAuthenticationFilter.CURRENT_USER;
        User user= userRepository.findByUsername(username);
        if(user==null){
            return null;
        }
        else {
            return cartRepository.findByUser(user);
        }

    }

    @Override
    public void deleteCart(Long cardId) {
        this.cartRepository.deleteById(cardId);
    }

    @Override
    public Cart updateCate( Long productId,Double quantily) {
        String username= JwtAuthenticationFilter.CURRENT_USER;
        Product product=productRepository.findById(productId).get();
       User user= userRepository.findByUsername(username);
        Cart cartr=cartRepository.findByUserAndProduct(user,product);
        cartr.setQuantily(quantily);
        cartr.setUser(user);
        cartr.setProduct(product);
        return cartRepository.save(cartr);
    }


}
