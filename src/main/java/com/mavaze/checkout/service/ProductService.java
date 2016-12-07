package com.mavaze.checkout.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.dto.SearchOutputDto;

public interface ProductService {
	
	@PreAuthorize("hasRole('INVENTORY_MANAGER')")
	long createProduct(ProductDto product);
	
	List<ProductDto> listProducts();

	ProductDto getProductById(long productId);
	
	SearchOutputDto<ProductDto> searchProductsWithQuery(String barcode, String[] categories);
}