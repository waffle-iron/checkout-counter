package com.mavaze.checkout.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private ProductService productService;

	@Test
	public void testGetProductByIdReturnsProduct() {
		
		// Dummy object
		ProductDto productDto = new ProductDto();
		productDto.setProductId(1L);
		productDto.setProductName("P1");
		productDto.setBarcode("ABC X23 782");
		productDto.setPrice(100D);
		productDto.setDescription("New Product");
		
		// Mock service call
		given(this.productService.getProductById(1L)).willReturn(productDto);
		
		// Hit the URL
		ProductDto body = this.restTemplate.withBasicAuth("cashier", "cashier").getForObject("/products/1", ProductDto.class);

		// Assert the response
		assertThat(body).isEqualTo(productDto);
	}

}
