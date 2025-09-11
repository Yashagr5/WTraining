package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beans.Payment;
import com.example.repo.PaymentRepo;

import jakarta.annotation.PostConstruct;

@Component
public class PaymentDataLoader {
	@Autowired
	PaymentRepo repo;
	
	@Autowired
	public PaymentDataLoader(PaymentRepo repo) {
		super();
		this.repo = repo;
	}

	
   @PostConstruct
	public void loadDummyData()
	{
		
		Payment payment1 = new Payment(1,"UPI" , 1);
	   
		Payment payment2 = new Payment(2,"Credit Card", 2);
		
		Payment payment3 = new Payment(3,"Debit card", 3);
		
		Payment payment4 = new Payment(4,"Cash", 4);
		   
		
		repo.save(payment1);
		repo.save(payment2);
		repo.save(payment3);
		repo.save(payment4);
		
		
		System.out.println("Data loaded safely");
	}
}
