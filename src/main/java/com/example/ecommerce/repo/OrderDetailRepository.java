package com.example.ecommerce.repo;

import com.example.ecommerce.model.OrderDetail;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    public List<OrderDetail>findByUser(User user);
    public List<OrderDetail>findByOrderStatus(String orderStatus);
}
