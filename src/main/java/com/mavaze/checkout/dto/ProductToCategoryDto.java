package com.mavaze.checkout.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ProductToCategoryDto {
	
	private Set<Integer> products;
	
	private Set<Integer> categories;
}