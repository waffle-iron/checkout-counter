package com.mavaze.checkout.dto;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class CategoryDto {
	
	private long categoryId;
	
	private String categoryName;
		
	@Range(min=0, max=100, message="Discount must be in between 0 and 100 percentage.")
	private int discount;
	
	private String description;
	
}