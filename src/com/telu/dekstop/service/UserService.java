/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telu.dekstop.service;

import com.telu.dekstop.model.User;
import java.util.List;

/**
 *
 * @author maula
 */
public interface UserService {
    
    public void insert(User user);
    public void update(User user);
    public void delete(User user);
    public List<String> getRoles();
    public List<User> select(int skip, int max);
    public User authenticate(String username, String password);
    public Long count();
    public User selectOne(Integer id);
    
    
}
