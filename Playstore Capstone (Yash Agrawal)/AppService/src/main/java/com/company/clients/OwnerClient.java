package com.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.dto.OwnerDTO;

@FeignClient(name = "OWNER-MS")
public interface OwnerClient {

    @GetMapping("/api/owners/{id}")
    OwnerDTO getOwnerById(@PathVariable("id") Long id);
    
    @GetMapping("/api/owners/name/{name}")
    OwnerDTO getOwnerByName(@PathVariable("name") String name);
}
