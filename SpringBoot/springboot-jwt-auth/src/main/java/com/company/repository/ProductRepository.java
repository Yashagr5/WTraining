package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
