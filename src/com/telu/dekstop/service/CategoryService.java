/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.telu.dekstop.service;

import com.telu.dekstop.model.Category;
import java.util.List;

/**
 *
 * @author maula
 */
public interface CategoryService {
    
    public void insert(Category category);
    public void update(Category category);
    public void delete(Category category);
    public List<Category> select(int skip, int max);
    public Long count();
    public Category selectOne(Integer id);
    
}
