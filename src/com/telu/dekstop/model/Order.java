/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maula
 */
public class Order implements Serializable{
    
    @TableColumn(number = 1, name = "ID")
    private Integer id;
    @TableColumn(number = 2, name = "Payment Method")
    private PaymentMethod payment_method;
    @TableColumn(number = 3, name = "Status Pesanan")
    private OrderStatus status;
    @TableColumn(number = 4, name = "Jumlah Kuantitas")
    private Integer totalQuantity;
    @TableColumn(number = 5, name = "Jumlah Barang")
    private Integer totalItems;
    @TableColumn(number = 6, name = "Total Harga")
    private float totalPrice;
    @TableColumn(number = 7, name = "Tanggal Pesanan")
    private Date orderDate;
    private List<OrderDetail> details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
