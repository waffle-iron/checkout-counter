package com.mavaze.checkout.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.dto.SearchOutputDto;
import com.mavaze.checkout.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST)
	public long createProduct(@Valid @RequestBody ProductDto product) {
		return productService.createProduct(product);
	}
	
	@ResponseBody
	@RequestMapping(value="/{productId}", method=RequestMethod.GET)
	public ProductDto getProductById(@PathVariable long productId) {
		return productService.getProductById(productId);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<ProductDto> listProducts() {
		return productService.listProducts();
	}
	
	@ResponseBody
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public SearchOutputDto<ProductDto> searchProductsWithQuery(@RequestParam(required=false) String barcode, 
			@RequestParam(required=false) String[] categories) {
		return productService.searchProductsWithQuery(barcode, categories);
	}

}