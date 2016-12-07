package com.mavaze.checkout.dto;

import lombok.Data;

@Data
public class ProductDto {

	private long productId;
	
	private String productName;
	
	private String barcode;
	
	private double price;
	
	private CategoryDto category;
	
	private String description;
	
}
