package com.example.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	Integer orderId;
	String product;
	Integer price;
	Integer quantity;
	Integer userId;
}
