/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.service.impl;

import com.telu.dekstop.util.Database;
import com.telu.dekstop.model.Category;
import com.telu.dekstop.model.Item;
import com.telu.dekstop.service.ItemService;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maula
 */
public class ItemServiceImpl implements ItemService{
    
    private final String INSERT_SQL = "insert into item(name, price, stock, category) values (?, ?, ?, ?);";
    private final String UPDATE_SQL = "update item set name = ?, price = ?, stock = ?, category = ? where id = ?;";
    private final String DELETE_SQL = "delete from item where id = ?;";
    private final String SELECT_SQL = "select id, name, price, stock, category from item limit ?, ?";
    private final String COUNT_SQL = "select count(id) as total from item";
    private final String UPDATE_STOCK = "update item set stock = stock - ? where id = ? and stock >= ?";
    private final String SELECT_ONE_SQL = "select id, name, price, stock, category from item where id = ?;";
    private final String SELECT_BY_CATEGORY = "select id, name, price, stock, category from item where category = ?";
    
    @Override
    public void insert(Item item){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_SQL) ){
            
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getStock());
            statement.setInt(4, item.getCategory().getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(Item item){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_SQL) ){
            
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getStock());
            statement.setInt(4, item.getCategory().getId());
            statement.setInt(5, item.getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(Item item){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(DELETE_SQL) ){
            
            statement.setInt(1, item.getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Item> select(int skip, int max){
        ArrayList<Item> list = new ArrayList<>(0);
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_SQL);) {

            statement.setInt(1, skip);
            statement.setInt(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getFloat("price"));
                    item.setStock(resultSet.getInt("stock"));
                    
                    Integer idCategory = resultSet.getInt("category");
                    Category category = Database.getCategoryService().selectOne(idCategory);
                    item.setCategory(category);
                    list.add(item);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }
        
    @Override
    public void updateStock(int id, int count){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_STOCK)){
           
            statement.setInt(1, count);
            statement.setInt(2, id);
            statement.setInt(3, count);
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Item select(Integer id) {
        Item item = null;
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_ONE_SQL);) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getFloat("price"));
                    item.setStock(resultSet.getInt("stock"));
                    
                    Integer idCategory = resultSet.getInt("category");
                    Category category = Database.getCategoryService().selectOne(idCategory);
                    item.setCategory(category);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return item;
        }
    }
    
    @Override
    public List<Item> selectByCategory(Integer categoryId){
        
        ArrayList<Item> list = new ArrayList<>();
        try(PreparedStatement statement =  Database.getConnection().prepareStatement(SELECT_BY_CATEGORY)){
            
            statement.setInt(1, categoryId);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Item item = new Item();
                    item.setId(resultSet.getInt("id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getFloat("price"));
                    item.setStock(resultSet.getInt("stock"));
                    
                    Integer idCategory = resultSet.getInt("category");
                    Category category = Database.getCategoryService().selectOne(idCategory);
                    item.setCategory(category);
                    
                    list.add(item);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Long count() {
        Long total = 0L;
        try (PreparedStatement statement = Database.getConnection().prepareStatement(COUNT_SQL);
                ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                total = resultSet.getLong("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return total;
        }
    }

    
    
}
    
    
    
