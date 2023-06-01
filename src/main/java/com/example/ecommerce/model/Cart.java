package com.example.ecommerce.model;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private Double quantily;

    public Double getQuantily() {
        return quantily;
    }

    public void setQuantily(Double quantily) {
        this.quantily = quantily;
    }

    public Cart(){}

    public Cart(Double quantily, Product product, User user) {
        this.quantily = quantily;
        this.product = product;
        this.user = user;
    }

    @OneToOne()
    private Product product;
    @OneToOne()
    private User user;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
