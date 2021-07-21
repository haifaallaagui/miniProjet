package com.example.demo.service;

import java.sql.Timestamp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repos.CategoryRepository;
import com.example.demo.models.Category;
@Service
public class CategoryService {
    private static CategoryRepository categoryRepository ;

    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public void addCategory(Category category){
        var timestamp = new Timestamp(System.currentTimeMillis());
        category.setDate_creation(timestamp);
        category.setDate_modif(null);

        categoryRepository.save(category);
    }

    public void updateCategory(Category category){
        var timestamp = new Timestamp(System.currentTimeMillis());
        category.setDate_modif(timestamp);
        categoryRepository.save(category);
    }}


