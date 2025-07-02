/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.service.impl;

import com.telu.dekstop.util.Database;
import com.telu.dekstop.model.User;
import com.telu.dekstop.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author maula
 */
public class UserServiceImpl implements UserService {
    private final String INSERT_SQL = "insert into user(username, pass, name, role) values (?, SHA2(?, 256),?,?);";
    private final String UPDATE_SQL = "update user set username = ?, pass = SHA2(?, 256), role = ? where id = ?;";
    private final String DELETE_SQL = "delete from user where id = ?;";
    private final String SELECT_SQL = "select id, username, pass, name, role from user limit ?, ?";
    private final String COUNT_SQL = "select count(id) as total from user";
    private final String SELECT_ONE_SQL = "select id, username, pass, role from user where id = ?;";
    private final String SELECT_ROLE = "select distinct role from user";
    private final String AUTHENTICATE = "select id, username, pass, role from user where username = ? and pass = SHA2(?,256)";
    
    @Override
    public void insert(User user){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_SQL) ){
            
            statement.setString(1, user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getRole());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(User user){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_SQL) ){
            
            statement.setString(1, user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());
            
            statement.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void delete(User user){
        try(PreparedStatement statement = Database.getConnection().prepareStatement(DELETE_SQL) ){
            
            statement.setInt(1, user.getId());
            
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<User> select(int skip, int max){
        ArrayList<User> list = new ArrayList<>(0);
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_SQL);) {

            statement.setInt(1, skip);
            statement.setInt(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("pass"));
                    user.setName(resultSet.getString("name"));
                    user.setRole(resultSet.getString("role"));
                    
//                    //mengambil nilai role dan konversi ke enum
//                    String roleString = resultSet.getString("role");
//                    UserRole role = UserRole.valueOf(roleString);
//                    user.setRole(role);
//                    
                    
                    list.add(user);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }
    
    @Override
    public List<String> getRoles(){
        List<String> roles = new ArrayList();
        try(PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_ROLE)){
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                roles.add(resultSet.getString("role"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
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
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return total;
        }
    }

    @Override
    public User selectOne(Integer id) {
        User user = null;
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_ONE_SQL);) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return user;
        }
  
    }

    @Override
    public User authenticate(String username, String password) {
        
        try(PreparedStatement statement = Database.getConnection().prepareStatement(AUTHENTICATE)){
            statement.setString(1, username);
            statement.setString(2, password);
            try(ResultSet resultSet =  statement.executeQuery()){
                if(resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("pass"));
                    user.setRole(resultSet.getString("role"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
