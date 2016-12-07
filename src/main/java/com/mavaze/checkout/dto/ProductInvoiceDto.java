package com.mavaze.checkout.dto;

import lombok.Data;

@Data
public class ProductInvoiceDto {

	private long productId;
	
	private String productName;
		
	private double price;
	
	private double discount;
}
