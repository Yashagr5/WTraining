package com.example.geh.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="Product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	public Product(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	private Long id;
	private String name;
	private double price;
}
