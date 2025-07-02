/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telu.dekstop.service;

import com.telu.dekstop.model.Order;
import com.telu.dekstop.model.OrderDetail;
import com.telu.dekstop.model.OrderStatus;
import java.util.List;

/**
 *
 * @author maula
 */
public interface OrderService {
    
    public int insert(Order order);
    public boolean updateStatus(Integer orderId, OrderStatus newStatus);
    public List<Order> selectAll(int skip, int max);
    public List<OrderDetail> getByOrderId(Integer orderId);
    public Long count();
    
}
