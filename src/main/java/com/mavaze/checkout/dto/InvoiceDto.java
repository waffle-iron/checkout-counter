package com.mavaze.checkout.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class InvoiceDto {
		
	private String invoiceNo;
	
	private List<ProductInvoiceDto> products;
	
	private double totalAmount;
	
	private Date dateOfTransaction;
	
	private String cashier;
	
}