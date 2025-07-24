package com.kt.quickkart.service;

import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.repository.CategoryRepo;
import com.kt.quickkart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuickKartService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<Category> getcategories() {
        return categoryRepo.findAll();
    }

    public List<Product> getproducts (Long categoryID){
        return productRepo.findByCategoryID(categoryID);
    }
}
