    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.telu.dekstop.service.impl;

import com.telu.dekstop.model.Item;
import com.telu.dekstop.model.Order;
import com.telu.dekstop.model.OrderDetail;
import com.telu.dekstop.model.OrderStatus;
import com.telu.dekstop.model.PaymentMethod;
import com.telu.dekstop.service.OrderService;
import com.telu.dekstop.util.Database;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maula
 */
public class OrderServiceImpl implements OrderService{
    
    private final String INSERT_ORDER = "INSERT INTO orders (payment_method, status, total_quantity, total_items, total_price, order_date) VALUES (?, ?, ?, ?, ?, ?)";
    private final String INSERT_DETAIL = "INSERT INTO order_detail (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
    private final String SELECT_ALL = "SELECT id, payment_method, status, total_quantity, total_items, total_price, order_date FROM orders LIMIT ? OFFSET ?";
    private final String UPDATE_STATUS = "UPDATE orders SET status = ? where id = ?";
    private final String SELECT_BY_ORDER_ID = "SELECT * FROM order_detail WHERE order_id = ?";
    private final String COUNT_SQL = "select count(id) as total from orders";
    
    @Override
    public int insert(Order order){
        
        Integer orderId = null;
        // Simpan order
        try(PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)){
            
            statement.setString(1, order.getPayment_method().getValue());
            statement.setString(2, order.getStatus().getValue());
            statement.setInt(3, order.getTotalQuantity());
            statement.setInt(4, order.getTotalItems());
            statement.setFloat(5, order.getTotalPrice());
            statement.setTimestamp(6, new Timestamp(order.getOrderDate().getTime()));
            
            int rowsAffected = statement.executeUpdate();
            
            if(rowsAffected == 0){
                Database.getConnection().rollback();
                return -1;
            }
            
            // Ambil id pesanan baru
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    order.setId(orderId); 
                } else {
                    Database.getConnection().rollback();
                    return -1;
                }
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Simpan detail pesanan
        for(OrderDetail detail : order.getDetails()) {
            try(PreparedStatement statement = Database.getConnection().prepareStatement(INSERT_DETAIL)){
                
                statement.setInt(1, order.getId());
                statement.setInt(2, detail.getItemId().getId());
                statement.setInt(3, detail.getQuantity());
                statement.setFloat(4, detail.getSubtotal());
                statement.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
        
        
        return orderId;
    }
    
    @Override
    public boolean updateStatus(Integer orderId, OrderStatus newStatus){
        
        try(PreparedStatement statement = Database.getConnection().prepareStatement(UPDATE_STATUS)){
            
            statement.setString(1, newStatus.getValue());
            statement.setInt(2, orderId);
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
        
    }
    
    @Override
    public List<Order> selectAll(int skip, int max){
        ArrayList<Order> list = new ArrayList<>(0);
        try (PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_ALL);) {

            statement.setInt(1, max);
            statement.setInt(2, skip);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    
                    // Isi field dari hasil query
                    order.setId(resultSet.getInt("id"));

                    // Konversi payment_method dari String ke PaymentMethod enum
                    String paymentMethodStr = resultSet.getString("payment_method");
                    order.setPayment_method(PaymentMethod.fromString(paymentMethodStr)); // tambahkan method di PaymentMethod

                    // Konversi status dari String ke OrderStatus enum
                    String statusStr = resultSet.getString("status");
                    order.setStatus(OrderStatus.fromString(statusStr));

                    order.setTotalQuantity(resultSet.getInt("total_quantity"));
                    order.setTotalItems(resultSet.getInt("total_items"));
                    order.setTotalPrice(resultSet.getFloat("total_price"));
                    order.setOrderDate(resultSet.getTimestamp("order_date"));

                    list.add(order);
                    
                    System.out.println();
                    
                }
            }catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    @Override
    public List<OrderDetail> getByOrderId(Integer orderId) {
        List<OrderDetail> details = new ArrayList<>();
        
        try(PreparedStatement statement = Database.getConnection().prepareStatement(SELECT_BY_ORDER_ID)){
            
            statement.setInt(1, orderId);
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    OrderDetail detail = new OrderDetail();
                    detail.setId(resultSet.getObject("id", Integer.class));
                    detail.setOrderId(orderId);
                    
                    int itemId = resultSet.getInt("item_id");
                    Item item = Database.getItemService().select(itemId); // Ambil data dari item service
                    detail.setItemId(item);
                    
                    detail.setQuantity(resultSet.getObject("quantity", Integer.class));
                    detail.setSubtotal(resultSet.getObject("subtotal", Float.class));
                    
                    details.add(detail);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return details;
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
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return total;
        }
    }
}
