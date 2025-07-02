/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.controller;

import com.telu.dekstop.model.Order;
import com.telu.dekstop.model.OrderDetail;
import com.telu.dekstop.model.OrderStatus;
import com.telu.dekstop.service.OrderService;
import com.telu.dekstop.util.Database;
import java.util.List;

/**
 *
 * @author maula
 */
public class OrderController {
    
    private final OrderService orderService;
    
    public OrderController(){
        this.orderService = Database.getOrderService();
    }
    
    public int createOrder(Order order) {
        return orderService.insert(order);
    }
    
    public boolean updateStatus(int orderId, OrderStatus status){
        return orderService.updateStatus(orderId, status);
    }
    
    public Long count(){
        return orderService.count();
    }
    
    public List<Order> getAllOrders(int skip, int max){
        return orderService.selectAll(skip, max);
    }
    
    public List<OrderDetail> getDetailByOrderId(int orderId){
        return orderService.getByOrderId(orderId);
    }
    
    
}
