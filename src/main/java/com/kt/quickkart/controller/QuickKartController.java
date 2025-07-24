package com.kt.quickkart.controller;

import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.CategoryRepo;
import com.kt.quickkart.repository.UserRepo;
import com.kt.quickkart.service.QuickKartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quickkart")
public class QuickKartController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QuickKartService quickKartService;

    @GetMapping("/getusers")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/getcategories")
    public List<Category> getCategories(){
        return quickKartService.getcategories();
    }

    @GetMapping("/getproducts")
    public List<Product> getProducts(@RequestParam("category_id") Long categoryId) {
        return quickKartService.getproducts(categoryId);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        user.setUserId(null);
        return userRepo.save(user);
    }
    @PostMapping("/addcategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = quickKartService.addCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestParam Long categoryId) {
        Product savedProduct = quickKartService.addProduct(product, categoryId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
