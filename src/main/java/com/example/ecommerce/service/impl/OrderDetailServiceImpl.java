package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.JwtAuthenticationFilter;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repo.CartRepository;
import com.example.ecommerce.repo.OrderDetailRepository;
import com.example.ecommerce.repo.ProductRepository;
import com.example.ecommerce.repo.UserRepository;
import com.example.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private static final String ORDER_PLACE="Placed";
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public void placeOrder(OrderInput orderInput,boolean isCartCheckout) throws Exception{
        List<OrderProductQuantity>productQuantityList=orderInput.getOrderProductQuantityList();
        for(OrderProductQuantity o: productQuantityList){
          Product product = productRepository.findById(o.getProductId()).get();
          String currentUser= JwtAuthenticationFilter.CURRENT_USER;
          User user=userRepository.findByUsername(currentUser);
            OrderDetail orderDetail=new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACE,
                    product.getProductDiscountedPrice()*o.getQuanlity(),
                    product,
                    user

            );

            if(o.getQuanlity()>product.getStock()){
                throw new Exception("User already present");
            }
                if(isCartCheckout){
                    List<Cart> cartList=  cartRepository.findByUser(user);
                    cartList.stream().forEach(x->cartRepository.deleteById(x.getCardId()));
                }
                product.setStock(product.getStock()-o.getQuanlity());
                orderDetailRepository.save(orderDetail);


        }
    }

    @Override
    public List<OrderDetail> listOrderDetail(String orderStatus) {
        List<OrderDetail>orderDetailList=new ArrayList<>();
        if(orderStatus.equals("All")){
         orderDetailRepository.findAll().forEach(x->orderDetailList.add(x));
        }
        else{
            orderDetailRepository.findByOrderStatus(orderStatus).forEach(x->orderDetailList.add(x));
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail markOrderAsDelivered(Long orderId) {
       OrderDetail orderDetail= orderDetailRepository.findById(orderId).get();
       if (orderDetail !=null){
           orderDetail.setOrderStatus("Delivered");
         return this.orderDetailRepository.save(orderDetail);
       }
       return null;
    }

}
