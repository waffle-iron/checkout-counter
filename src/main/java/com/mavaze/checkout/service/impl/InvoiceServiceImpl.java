package com.mavaze.checkout.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mavaze.checkout.domain.Invoice;
import com.mavaze.checkout.domain.Product;
import com.mavaze.checkout.dto.InvoiceDto;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.dto.ProductInvoiceDto;
import com.mavaze.checkout.exception.NotFoundException;
import com.mavaze.checkout.repository.InvoiceRepository;
import com.mavaze.checkout.service.InvoiceService;
import com.mavaze.checkout.service.ProductService;
import com.mavaze.checkout.utils.ConversionUtils;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public InvoiceDto getInvoice(String invoiceNo) {
		
		double totalAmount = 0d;
		InvoiceDto invoiceDto = null;
		List<ProductInvoiceDto> productInvoiceDtos = new ArrayList<>();
		
		List<Invoice> invoice = invoiceRepository.findByInvoiceNo(invoiceNo);
		
		if(CollectionUtils.isEmpty(invoice)) {
			throw new NotFoundException("invoice.not.found.exception", new Object[]{invoiceNo});
		}
		
		for(Invoice invoiceProduct : invoice) { 
			if(invoiceDto == null) {
				invoiceDto = new InvoiceDto();
				invoiceDto.setInvoiceNo(invoiceProduct.getInvoiceNo());
				invoiceDto.setDateOfTransaction(invoiceProduct.getTransactionDate());
				if(invoiceProduct.getCashier() != null) {
					invoiceDto.setCashier(invoiceProduct.getCashier().getUserName());
				}
			}
			Product product = invoiceProduct.getProduct();
			ProductInvoiceDto productInvoiceDto = ConversionUtils.convertProductToProductInvoiceDto(product);
			totalAmount += invoiceProduct.getPrice() * (1 - invoiceProduct.getDiscount() / 100);
			productInvoiceDtos.add(productInvoiceDto);
		}

		invoiceDto.setProducts(productInvoiceDtos);
		invoiceDto.setTotalAmount(totalAmount);
		
		return invoiceDto;
	}
	
	@Override
	@Transactional
	public InvoiceDto generateInvoice(List<ProductDto> productDtos, boolean save) {
		
		InvoiceDto invoiceDto = new InvoiceDto();
		
		if(StringUtils.isEmpty(invoiceDto.getInvoiceNo())) {
			String invoiceNo = UUID.randomUUID().toString();
			invoiceDto.setInvoiceNo(invoiceNo);
		}
		
		List<ProductInvoiceDto> invoiceProducts = new ArrayList<>();
		List<Invoice> invoices = new ArrayList<>();
		
		double totalAmount = 0d;
		
		for(ProductDto productDto : productDtos) {
			
			productDto = productService.getProductById(productDto.getProductId());
			
			ProductInvoiceDto invoiceProduct = ConversionUtils.convertProductDtoToProductInvoiceDto(productDto);		
			invoiceProducts.add(invoiceProduct);
			invoiceDto.setProducts(invoiceProducts);

			totalAmount += invoiceProduct.getPrice() * (1 - invoiceProduct.getDiscount() / 100);
			
			if(save) {
				Invoice invoice = ConversionUtils.setProductDtoToInvoice(productDto);
				invoice.setInvoiceNo(invoiceDto.getInvoiceNo());
				invoice.setTransactionDate(new Date());
				invoices.add(invoice);
			}
		}

		invoiceDto.setDateOfTransaction(new Date());
		invoiceDto.setTotalAmount(totalAmount);

		if(save) {
			invoiceRepository.save(invoices);
		}

		return invoiceDto;
	}
	
}
