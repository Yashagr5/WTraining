package com.example.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.beans.Payment;

@FeignClient(name ="payment-ms" , url="http://localhost:8085")
public interface PaymentFeignClient {
	
	@GetMapping("/payments/{userId}")
	List<Payment> getSpecificPayment(@PathVariable("userId") int userId);

}
