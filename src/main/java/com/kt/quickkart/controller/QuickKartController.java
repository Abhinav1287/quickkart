package com.kt.quickkart.controller;

import com.kt.quickkart.dto.OrderRequestDTO;
import com.kt.quickkart.dto.OrderResponseDTO;
import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Orders;
import com.kt.quickkart.entity.Product;
import com.kt.quickkart.entity.User;
import com.kt.quickkart.repository.CategoryRepo;
import com.kt.quickkart.repository.OrdersRepo;
import com.kt.quickkart.repository.UserRepo;
import com.kt.quickkart.service.OrderService;
import com.kt.quickkart.service.QuickKartService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quickkart")
public class QuickKartController {

    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QuickKartService quickKartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/getusers") //working
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/getcategories") //working
    public List<Category> getCategories(){
        return quickKartService.getcategories();
    }

    @GetMapping("/getproducts") //working
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


    @PostMapping("/order")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.placeOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getorders")
    public List<Orders> getOrderr(){
        return  ordersRepo.findAll();
    }


    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long orderId) {
        OrderResponseDTO response = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(response);
    }


}
