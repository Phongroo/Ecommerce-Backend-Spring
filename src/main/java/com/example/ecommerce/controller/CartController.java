package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/addToCart/{productId}")
    public Cart addToCart(@RequestBody Cart cart, @PathVariable(name = "productId")Long productId){
        return cartService.addCart(cart,productId);
    }
    @GetMapping("/cart")
    public List<Cart>getAll(){
        return cartService.listCart();
    }
    @DeleteMapping("/cart/{cardId}")
    public void deleteCart(@PathVariable(name = "cardId") Long cardId){
        this.cartService.deleteCart(cardId);
    }
    @PutMapping("/cart/{productId}")
    public Cart updateCart(@PathVariable(name = "productId")Long productId,@RequestParam(name = "quantily") Double quantily){
        return this.cartService.updateCate(productId,quantily.doubleValue());
    }
}
