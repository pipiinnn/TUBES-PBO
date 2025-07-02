/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.service.impl;

import com.telu.dekstop.service.*;
import com.telu.dekstop.util.Database;
import com.telu.dekstop.model.Category;
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
public class CategoryServiceImpl implements CategoryService {
    
    private final String INSERT_SQL = "insert into category(name) values (?);";
    private final String UPDATE_SQL = "update category set name = ? where id = ?;";
    private final String DELETE_SQL = "delete from category where id = ?;";
    private final String SELECT_SQL = "select id, name from category limit ?, ?";
    private final String COUNT_SQL = "select count(id) as total from category";
    private final String SELECT_ONE_SQL = "select id, name from category where id = ?;";
    
    @Override
    public void insert(Category category){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_SQL) ){
            
            statement.setString(1, category.getName());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(Category category){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_SQL) ){
            
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(Category category){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(DELETE_SQL) ){
            
            statement.setInt(1, category.getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Category> select(int skip, int max){
        ArrayList<Category> list = new ArrayList<>(0);
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_SQL);) {

            statement.setInt(1, skip);
            statement.setInt(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                    list.add(category);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
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
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return total;
        }
    }

    @Override
    public Category selectOne(Integer id) {
        Category category = null;
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_ONE_SQL);) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return category;
        }
  
    }

   
 
    
}
    
    
    
