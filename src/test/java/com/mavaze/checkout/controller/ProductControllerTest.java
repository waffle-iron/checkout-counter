package com.mavaze.checkout.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService productService;

	@Test
	@WithMockUser
	public void testGetProductByIdReturnsProduct() throws Exception {
		
		ProductDto productDto = new ProductDto();
		productDto.setProductId(1L);
		productDto.setProductName("P1");
		productDto.setBarcode("ABC X23 782");
		productDto.setPrice(100D);
		productDto.setDescription("New Product");
		
		given(this.productService.getProductById(1L)).willReturn(productDto);
		
		this.mvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("{\"productId\":1,\"productName\":\"P1\",\"barcode\":\"ABC X23 782\",\"price\":100.0,\"category\":null,\"description\":\"New Product\"}"));
	}
}
