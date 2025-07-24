package com.kt.quickkart.controller;

import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.CategoryRepo;
import com.kt.quickkart.repository.UserRepo;
import com.kt.quickkart.service.QuickKartService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
