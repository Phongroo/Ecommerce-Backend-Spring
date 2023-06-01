package com.example.ecommerce.service;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.OrderInput;

import java.util.List;

public interface OrderDetailService {
    public void placeOrder(OrderInput orderInput,boolean isCartCheckout) throws Exception;
    List<OrderDetail>listOrderDetail(String orderStatus);
    public OrderDetail markOrderAsDelivered(Long orderId);

}
