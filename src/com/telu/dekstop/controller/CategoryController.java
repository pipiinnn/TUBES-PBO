/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.controller;

import com.telu.dekstop.model.Category;
import com.telu.dekstop.service.CategoryService;
import com.telu.dekstop.util.Database;
import java.util.List;

/**
 *
 * @author maula
 */
public class CategoryController {
    
    private final CategoryService service;
    
    public CategoryController(){
        this.service = Database.getCategoryService();
    } 
    
    public boolean insert(Category category){
        try {
            service.insert(category);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Category category){
        try {
            service.update(category);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Category category){
        try {
            service.delete(category);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Category> getAll(int skip, int max){
        return service.select(skip, max);
    }
    
    public Category getById(Integer id){
        return service.selectOne(id);
    }
    
    public Long count(){
        return service.count();
    }
}
