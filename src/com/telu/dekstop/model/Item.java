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
public class Item implements Serializable{
    
    @TableColumn(number = 1, name = "id")
    private Integer id;
    @TableColumn(number = 2, name = "nama")
    private String name;
    @TableColumn(number = 3, name = "harga")
    private float price;
    @TableColumn(number = 4, name = "stok")
    private Integer stock;
    @TableColumn(number = 5, name = "kategori")
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
