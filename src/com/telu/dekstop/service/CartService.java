/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telu.dekstop.service;

import com.telu.dekstop.model.Cart;
import java.util.List;

/**
 *
 * @author maula
 */
public interface CartService {
    
    public void addToCart(Cart cart);
    public boolean updateCart(int newQuantity,Cart cart);
    public boolean updateAllStock(Cart cart);
    public void delete(Cart cart);
    public void deleteAll(boolean isCheckout);
    public List<Cart> select();
    public int getTotal();
}

