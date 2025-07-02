/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.model;

/**
 *
 * @author maula
 */
public class Admin extends User {
    
    public Admin(int id, String username, String password, String name){
        super(id, username, password, name, "Admin");
    }
    
    
}
