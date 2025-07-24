package com.kt.quickkart.repository;

import com.kt.quickkart.entity.Category;
import com.kt.quickkart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category= ?1")
    List<Product> findByCategoryID(Long category_id);
}

