package com.kt.quickkart.service;

import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.CategoryRepo;
import com.kt.quickkart.repository.ProductRepo;
import com.kt.quickkart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuickKartService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Category> getcategories() {
        return categoryRepo.findAll();
    }
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Product> getproducts (Long categoryId){
        return productRepo.findByCategoryID(categoryId);
    }
    @Autowired
    private UserRepo userRepo;
    public User registerUser(User user) {
        return userRepo.save(user);
    }
    public Category addCategory(Category category) {
        if (category.getProducts() != null) {
            for (Product product : category.getProducts()) {
                product.setCategory(category);  // Associate product with category
            }
        }
        return categoryRepo.save(category); // Save category and products
    }
    public Product addProduct(Product product, Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Invalid category ID"));

        product.setCategory(category); // Link product to category
        return productRepo.save(product);
    }
}
