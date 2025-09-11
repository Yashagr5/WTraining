package com.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.dto.CategoryDTO;

@FeignClient(name = "category-ms")
public interface CategoryClient {

    @GetMapping("/api/categories/{id}")
    CategoryDTO getCategoryById(@PathVariable("id") Long long1);
    
    @GetMapping("/api/categories/name/{name}")
    CategoryDTO getCategoryByName(@PathVariable("name") String name);
}
