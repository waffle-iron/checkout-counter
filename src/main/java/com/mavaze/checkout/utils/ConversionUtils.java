package com.mavaze.checkout.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.mavaze.checkout.domain.Category;
import com.mavaze.checkout.domain.Invoice;
import com.mavaze.checkout.domain.Product;
import com.mavaze.checkout.dto.CategoryDto;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.dto.ProductInvoiceDto;

@Component
public class ConversionUtils {
	
	public static Product convertProductDtoToProduct(ProductDto productDto) {
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setBarcode(productDto.getBarcode());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		return product;
	}

	public static ProductInvoiceDto convertProductDtoToProductInvoiceDto(ProductDto productDto) {
		
		ProductInvoiceDto invoiceProduct = new ProductInvoiceDto();
		
		invoiceProduct.setProductId(productDto.getProductId());
		invoiceProduct.setProductName(productDto.getProductName());
		invoiceProduct.setPrice(productDto.getPrice());
		
		if(productDto.getCategory() != null) {
			invoiceProduct.setDiscount(productDto.getCategory().getDiscount());
		} else {
			invoiceProduct.setDiscount(0);
		}
		
		return invoiceProduct;
	}

	public static Invoice setProductDtoToInvoice(ProductDto productDto) {
		
		Product product = convertProductDtoToProduct(productDto);
		
		Invoice invoice = new Invoice();
		invoice.setProduct(product);
		invoice.setPrice(product.getPrice());
		
		if(product.getCategory() != null) {
			invoice.setDiscount(product.getCategory().getDiscount());
		} else {
			invoice.setDiscount(0);
		}
		
		return invoice;
	}

	public static ProductDto convertProductToProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		
		Category category = product.getCategory();
		if(category!=null) {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(category, categoryDto);
			productDto.setCategory(categoryDto);
		}
		
		return productDto;
	}

	public static ProductInvoiceDto convertProductToProductInvoiceDto(Product product) {
		return convertProductDtoToProductInvoiceDto(convertProductToProductDto(product));
	}

}
