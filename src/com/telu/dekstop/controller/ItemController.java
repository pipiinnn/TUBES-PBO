/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.controller;

import com.telu.dekstop.model.Item;
import com.telu.dekstop.util.Database;
import com.telu.dekstop.service.ItemService;
import java.util.List;

/**
 *
 * @author maula
 */
public class ItemController {
    
    private final ItemService service;
    
    public ItemController(){
        this.service = Database.getItemService();
    }
    
    public boolean insert(Item item){
        try {
            service.insert(item);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Item item){
        try {
            service.update(item);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStock(int id, int count){
        try{
            service.updateStock(id, count);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Item item){
        try{
            service.delete(item);
            return true; 
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Long count(){
        return service.count();
    }
    
    public List<Item> getAll(int skip, int max){
        return service.select(skip, max);
    }
    
    public Item getById(Integer id){
        return service.select(id);
    }
    
    public List<Item> getByCategory(int categoryId){
        return service.selectByCategory(categoryId);
    }
}
