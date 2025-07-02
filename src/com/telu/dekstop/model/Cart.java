/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;

/**
 *
 * @author maula
 */
public class Cart implements Serializable{
    
    private Integer id;
    @TableColumn(number = 1, name = "item")
    private Item item_id;
    @TableColumn(number = 2, name = "quantity")
    private Integer quantity;
    @TableColumn(number = 3, name = "Total Harga")
    private Integer subTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Item getItem_id() {
        return item_id;
    }

    public void setItem_id(Item item_id) {
        this.item_id = item_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return item_id + " x " + quantity + " =Rp." + subTotal; 
                
    }
    
    
    
}
