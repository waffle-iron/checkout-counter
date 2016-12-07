package com.mavaze.checkout.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavaze.checkout.domain.Category;
import com.mavaze.checkout.domain.Product;
import com.mavaze.checkout.dto.CategoryDto;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.exception.NotFoundException;
import com.mavaze.checkout.repository.CategoryRepository;
import com.mavaze.checkout.repository.ProductRepository;
import com.mavaze.checkout.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public long createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);
		category = categoryRepository.save(category);
		return category.getCategoryId();
	}
	
	@Override
	public CategoryDto getCategoryById(long categoryId) {
		Category category = categoryRepository.findOne(categoryId);
		CategoryDto categoryDto = new CategoryDto();
		BeanUtils.copyProperties(category, categoryDto);
		return categoryDto;
	}
	
	@Override
	public List<CategoryDto> listCategories() {
		
		List<Category> categories = categoryRepository.findAll();
		
		List<CategoryDto> categoryDtos = new ArrayList<>();
		
		for(Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(category, categoryDto);
			categoryDtos.add(categoryDto);
		}
				
		return categoryDtos;
	}

	@Override
	@Transactional
	public boolean assignCategoryToProducts(long categoryId, List<ProductDto> productDtos) {
		 
		Category category = categoryRepository.findOne(categoryId);
		
		if(category != null) {
			for(ProductDto productDto : productDtos) {
				Product product = productRepository.findOne(productDto.getProductId());
				product.setCategory(category);
				productRepository.save(product);
			}
			return true;
		} else {
			throw new NotFoundException("category.not.found.exception", new Object[]{categoryId});
		}		
	}

	@Override
	@Transactional
	public boolean unassignCategoryFromProducts(long categoryId, List<ProductDto> productDtos) {
		
		Category category = categoryRepository.findOne(categoryId);
		
		if(category != null) {
			for(ProductDto productDto : productDtos) {
				Product product = productRepository.findOne(productDto.getProductId());
				if(product.getCategory() != null && product.getCategory().getCategoryId() == categoryId) {
					product.setCategory(null);
					productRepository.save(product);
				}
			}
			return true;
		} else {
			throw new NotFoundException("category.not.found.exception", new Object[]{categoryId});
		}
	}

}
