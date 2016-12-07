package com.mavaze.checkout.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mavaze.checkout.domain.Product;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.repository.ProductRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testGetProductByIdReturnsProduct() {
		// Create a mock object
		Product product = new Product();
		product.setProductName("TP1");
		
		// Mock the repository call
		given(this.productRepository.findOne(1L)).willReturn(product);
		
		// Test the service
		ProductDto productDto = productService.getProductById(1L);
		
		// Assert the response
		assertThat(productDto.getProductName()).isEqualTo("TP1");
	}
}
