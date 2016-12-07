package com.mavaze.checkout.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mavaze.checkout.domain.Product;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.dto.SearchOutputDto;
import com.mavaze.checkout.exception.NotFoundException;
import com.mavaze.checkout.repository.ProductRepository;
import com.mavaze.checkout.service.ProductService;
import com.mavaze.checkout.utils.ConversionUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public long createProduct(ProductDto productDto) {
		Product product = ConversionUtils.convertProductDtoToProduct(productDto);
		product = productRepository.save(product);
		return product.getProductId();
	}
	
	@Override
	public ProductDto getProductById(long productId) {
		Product product = productRepository.findOne(productId);
		if(product==null) {
			throw new NotFoundException("product.not.found.exception", new Object[]{productId});
		}
		return ConversionUtils.convertProductToProductDto(product);
	}
	
	@Override
	public List<ProductDto> listProducts() {
		
		List<Product> products = productRepository.findAll();
		
		List<ProductDto> productDtos = new ArrayList<>();
		for(Product product : products) {
			ProductDto productDto = ConversionUtils.convertProductToProductDto(product);
			productDtos.add(productDto);
		}
				
		return productDtos;
	}
	
	@Override
	public SearchOutputDto<ProductDto> searchProductsWithQuery(String barcode, String[] categories) {
		
		List<Product> products;
		
		if(!StringUtils.isEmpty(barcode)) {
			 products = productRepository.findByBarcode(barcode);
		} else if (!CollectionUtils.arrayToList(categories).isEmpty()) {
			products = productRepository.findByCategoryName(categories);
		} else {
			products = productRepository.findAll();
		}
		
		List<ProductDto> productDtos = new ArrayList<>();
		for(Product product : products) {
			ProductDto productDto = ConversionUtils.convertProductToProductDto(product);
			productDtos.add(productDto);
		}
		
		SearchOutputDto<ProductDto> searchOutputDto = new SearchOutputDto<ProductDto>();
		searchOutputDto.setEntities(productDtos);
		searchOutputDto.setTotalCount(productDtos.size());
		
		return searchOutputDto;
	}

}
