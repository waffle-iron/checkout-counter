package com.mavaze.checkout.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mavaze.checkout.dto.CategoryDto;
import com.mavaze.checkout.dto.ProductDto;

public interface CategoryService {
	
	@PreAuthorize("hasAnyRole('SALES_MANAGER', 'ADMIN')")
	long createCategory(CategoryDto categoryDto);
		
	List<CategoryDto> listCategories();
	
	CategoryDto getCategoryById(long categoryId);

	@PreAuthorize("hasAnyRole('SALES_MANAGER', 'ADMIN')")
	boolean assignCategoryToProducts(long categoryId, List<ProductDto> productDtos);

	@PreAuthorize("hasAnyRole('SALES_MANAGER', 'ADMIN')")
	boolean unassignCategoryFromProducts(long categoryId, List<ProductDto> productDtos);

}