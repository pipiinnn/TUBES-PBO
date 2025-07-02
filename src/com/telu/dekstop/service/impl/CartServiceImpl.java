/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.service.impl;

import com.telu.dekstop.service.*;
import com.telu.dekstop.util.Database;
import com.telu.dekstop.model.Cart;
import com.telu.dekstop.model.Item;
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
public class CartServiceImpl implements CartService {

    private final String INSERT_SQL = "insert into cart(item_id, quantity, subtotal) VALUES (?,?,?)";
    private final String UPDATE_CART = "update cart set quantity = ?, subtotal = ? where id = ?";
    private final String UPDATE_STOCK_PLUS = "update item set stock = stock - ? where id = ? and stock >= ?";
    private final String UPDATE_STOCK_MINUS = "update item set stock = stock + ? where id = ?";
    private final String DELETE_SQL = "delete from cart where id = ?;";
    private final String SELECT_SQL = "SELECT id, item_id, quantity, subtotal FROM cart";
    private final String TOTAL_PRICE = "select sum(subtotal) as total from cart;";
    private final String DELETE_ALL = "delete from cart";

    @Override
    public void addToCart(Cart cart) {
        try (PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_SQL)) {

            statement.setInt(1, cart.getItem_id().getId());
            statement.setInt(2, cart.getQuantity());
            statement.setInt(3, cart.getSubTotal());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean updateCart(int newQuantity, Cart cart) {

        // menghitung selisih jumlah stok
        int oldQty = cart.getQuantity();
        int difference = newQuantity - oldQty; // Jika positif kurangi stock(item) dan tambahkan quantity(cart)
        // dan jika negatif tambahkan stock(item) dan kurangi quantity(cart)

        // Update Table Item
        if (difference > 0) {
            try (PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_STOCK_PLUS)) {

                statement.setInt(1, difference);
                statement.setInt(2, cart.getItem_id().getId());
                statement.setInt(3, difference);

                int update = statement.executeUpdate();
                if (update == 0) {
                    Database.getConnection().rollback();
                    return false;
                }

            } catch (SQLException ex) {
                Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try (PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_STOCK_MINUS)) {

                statement.setInt(1, Math.abs(difference));
                statement.setInt(2, cart.getItem_id().getId());

                statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Update table cart
        int price = (int) cart.getItem_id().getPrice();
        int total = newQuantity * price;

        try (PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_CART)) {

            statement.setInt(1, newQuantity);
            statement.setInt(2, total);
            statement.setInt(3, cart.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public void delete(Cart cart) {

        int deleteQty = cart.getQuantity();

        // Update stock item
        try (PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_STOCK_MINUS)) {

            statement.setInt(1, deleteQty);
            statement.setInt(2, cart.getItem_id().getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Delete row in table cart
        try (PreparedStatement statement = Database.getConnection().prepareStatement(DELETE_SQL)) {

            statement.setInt(1, cart.getId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAll(boolean isCheckout) {

        if (!isCheckout) {
            try (PreparedStatement selectStatement = Database.getConnection().prepareStatement(SELECT_SQL); ResultSet resultSet = selectStatement.executeQuery()) {

                // Update stock
                try (PreparedStatement updateStockStmt = Database.getConnection().prepareStatement(UPDATE_STOCK_MINUS)) {
                    while (resultSet.next()) {
                        int itemId = resultSet.getInt("item_id");
                        int quantity = resultSet.getInt("quantity");

                        updateStockStmt.setInt(1, quantity);
                        updateStockStmt.setInt(2, itemId);
                        updateStockStmt.addBatch();
                    }
                    updateStockStmt.executeBatch();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Ambil data

        try (PreparedStatement deleteStatement = Database.getConnection().prepareStatement(DELETE_ALL)) {
            deleteStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Cart> select() {
        ArrayList<Cart> list = new ArrayList<>(0);
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_SQL);) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = new Cart();
                    cart.setId(resultSet.getInt("id"));

                    int itemId = resultSet.getInt("item_id");
                    Item item = Database.getItemService().select(itemId);
                    cart.setItem_id(item);

                    cart.setQuantity(resultSet.getInt("quantity"));
                    cart.setSubTotal(resultSet.getInt("subtotal"));

                    list.add(cart);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    @Override
    public int getTotal() {
        int total = 0;
        try (PreparedStatement statement = Database.getConnection().prepareStatement(TOTAL_PRICE)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getInt("total");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    @Override
    public boolean updateAllStock(Cart cart) {
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_SQL)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer result = resultSet.getInt("id");
                    System.out.println(result);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
