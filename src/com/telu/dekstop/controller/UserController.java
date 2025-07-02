/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.controller;

import com.telu.dekstop.model.User;
import com.telu.dekstop.util.Database;
import com.telu.dekstop.service.UserService;
import java.util.List;

/**
 *
 * @author maula
 */
public class UserController {
    
    private final UserService service;
    
    public UserController(){
        this.service = Database.getUserService();
    }
    
    public boolean insert(User user){
        try {
            service.insert(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(User user){
        try {
            service.update(user);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(User user){
        try{
            service.delete(user);
            return true; 
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> getRoles(){
        return service.getRoles();
    }
    
    public List<User> getAll(int skip, int max){
        return service.select(skip, max);
    }
    
    public Long count(){
        return service.count();
    }
    
    public User getById(Integer id){
        return service.selectOne(id);
    }
    
    public User authenticate(String username, String pass){
        return service.authenticate(username, pass);
    }
}
