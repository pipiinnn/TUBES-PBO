/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telu.dekstop.service;

import com.telu.dekstop.model.Item;
import java.util.List;

/**
 *
 * @author maula
 */
public interface ItemService {
    
    public void insert(Item item);
    public void update(Item item);
    public void delete(Item item);
    public List<Item> select(int skip, int max);
    public void updateStock(int id, int count);
    public Long count();
    public Item select(Integer id);
    public List<Item> selectByCategory(Integer categoryId);
    
}
