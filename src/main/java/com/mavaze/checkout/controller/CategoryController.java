package com.mavaze.checkout.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavaze.checkout.dto.CategoryDto;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method=RequestMethod.POST)
	private long createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}
	
	@RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
	public CategoryDto getCategoryById(@PathVariable long categoryId) {
		return null;
	}
	
	@RequestMapping(value="/{categoryId}", method=RequestMethod.PUT)
	public boolean updateCategory(@PathVariable long categoryId, @RequestBody CategoryDto categoryDto) {
		return false;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CategoryDto> listCategories() {
		return null;
	}
	
	@RequestMapping(value="/{categoryId}/products", method=RequestMethod.GET)
	public List<ProductDto> getProductsAssignedToCategory(@PathVariable long categoryId) {
		return null;
	}
	
	@RequestMapping(value="/{categoryId}/products", method=RequestMethod.POST)
	private boolean assignUnassignCategoriesToUsers(@RequestBody List<ProductDto> productDtos,
			@PathVariable int categoryId, @RequestParam String action) {
		
		if(StringUtils.isEmpty(action)) {
			log.warn("Action can't be empty.");
			return false;
		} else if("assign".equals(action)) {
			log.info("Assignment action is called.");
			return categoryService.assignCategoryToProducts(categoryId, productDtos);
		} else if("unassign".equals(action)) {
			log.info("Unassignment action is called.");
			return categoryService.unassignCategoryFromProducts(categoryId, productDtos);
		} else {
			log.warn("Action is not supported.");
			return false;
		}
	}
	
}
