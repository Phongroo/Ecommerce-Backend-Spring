package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    List<Product> listProduct();
    public void deleteProduct(Long productId);
    public Product getProduct(Long productId);
    public List<Cart> getProductDetail(boolean isSingleProductCheckout);

}
