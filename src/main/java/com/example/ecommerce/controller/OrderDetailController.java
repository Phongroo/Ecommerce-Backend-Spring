package com.example.ecommerce.controller;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.OrderInput;
import com.example.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
    @PostMapping("/placeOrder/{isCartCheckout}")
    public void placeOrder(@PathVariable(name = "isCartCheckout") boolean isCartCheckout, @RequestBody OrderInput orderInput)throws Exception {
        orderDetailService.placeOrder(orderInput,isCartCheckout);
    }
    @GetMapping("/admin/getAllOrder/{orderStatus}")
    public List<OrderDetail>detailList(@PathVariable(name = "orderStatus") String orderStatus){
        return this.orderDetailService.listOrderDetail(orderStatus);
    }

    @GetMapping("/admin/markOrderAsDelivered/{orderId}")
    public OrderDetail markOrderAsDelivered(@PathVariable(name="orderId") Long orderId){
      return this.orderDetailService.markOrderAsDelivered(orderId);
    }

}
