/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.telu.dekstop.service.CartService;
import com.telu.dekstop.service.CategoryService;
import com.telu.dekstop.service.ItemService;
import com.telu.dekstop.service.OrderService;
import com.telu.dekstop.service.impl.CartServiceImpl;
import com.telu.dekstop.service.impl.CategoryServiceImpl;
import com.telu.dekstop.service.impl.ItemServiceImpl;
import com.telu.dekstop.service.impl.OrderServiceImpl;
import com.telu.dekstop.service.impl.UserServiceImpl;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maula
 */
public class Database {
    
    private static Connection connection;
    
    private static CategoryService categoryService;
    private static ItemService itemService;
    private static UserServiceImpl userService;
    private static CartService cartService;
    private static OrderService orderService;
    
    public static CategoryService getCategoryService(){
        if(categoryService == null){
            categoryService = new CategoryServiceImpl();
        }
        return categoryService;
    }
    
    public static ItemService getItemService(){
        if(itemService == null){
            itemService = (ItemService) new ItemServiceImpl();
        }
        return itemService;
    }
    
    public static UserServiceImpl getUserService(){
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }
    
    public static CartService getCartService(){
        if(cartService == null){
            cartService = (CartService) new CartServiceImpl() {};
        }
        return cartService;
    }
    
    public static OrderService getOrderService(){
         if(orderService == null){
             orderService = new OrderServiceImpl();
         }
         return orderService;
    }
    public static Connection getConnection(){
        
        if (connection == null){
            try{
                Properties properties = new Properties();
                properties.load(Database.class.getResourceAsStream("/com/telu/dekstop/util/database.properties"));
                
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser(properties.getProperty("username"));
                dataSource.setPassword(properties.getProperty("password"));
                dataSource.setServerName(properties.getProperty("host"));
                dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
                dataSource.setDatabaseName(properties.getProperty("database"));
                
                connection = dataSource.getConnection();
                System.out.println("Database terhubung");
                
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return connection;
    }
    
    public static void testConnection() {
        connection = getConnection();
        if(connection != null){
            System.out.println("Koneksi OK");
        }else {
            System.out.println("Koneksi gagal");
        }
    }
    
    public static void main(String[] args) {
        Database.testConnection();
    }
}
