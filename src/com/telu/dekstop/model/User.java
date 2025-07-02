/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

import com.stripbandunk.jwidget.annotation.TableColumn;

/**
 *
 * @author maula
 */
public class User {
    
    @TableColumn(number = 1, name = "id")
    private Integer id;
    @TableColumn(number = 2, name = "username")
    private String username;
    @TableColumn(number = 3, name = "password")
    private String password;
    @TableColumn(number = 4, name = "name")
    private String name;
    @TableColumn(number = 5, name = "role")
    private String role;
    
    
    public User(){
        
    }
    
    public User(Integer id, String username, String password, String name, String role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
