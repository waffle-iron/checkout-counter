package com.mavaze.checkout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mavaze.checkout.dto.InvoiceDto;
import com.mavaze.checkout.dto.ProductDto;
import com.mavaze.checkout.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
		
	@ResponseBody
	@RequestMapping(value="/{invoiceNo}", method=RequestMethod.GET)
	public InvoiceDto getInvoiceByInvoiceNumber(@PathVariable String invoiceNo) {
		return invoiceService.getInvoice(invoiceNo);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public InvoiceDto generateInvoice(@RequestBody List<ProductDto> productDtos, 
			@RequestParam(defaultValue="false") boolean save) {
		return invoiceService.generateInvoice(productDtos, save);
	}
	
}