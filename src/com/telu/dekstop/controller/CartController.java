    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.controller;

import com.telu.dekstop.model.Cart;
import com.telu.dekstop.service.CartService;
import com.telu.dekstop.util.Database;
import java.util.List;

/**
 *
 * @author maula
 */
public class CartController {
    
    private final CartService service;
    
    public CartController(){
        this.service = Database.getCartService();
    }
    
    public boolean addToCart(Cart cart){
        try{
            service.addToCart(cart);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateCart(int newQuantity, Cart cart){
        try{
            service.updateCart(newQuantity, cart);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateAllStock(Cart cart){
        return service.updateAllStock(cart);
    }
    
    public boolean deleteCart(Cart cart){
        try{
            service.delete(cart);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean clearCart(boolean isCheckout){
        try {
            service.deleteAll(isCheckout);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<Cart> getAllItems(){
        return service.select();
    }
    
    public int getTotalPrice(){
        return service.getTotal();
    }
}
