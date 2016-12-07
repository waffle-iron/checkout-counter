package com.mavaze.checkout.service;

import java.util.List;

import com.mavaze.checkout.dto.InvoiceDto;
import com.mavaze.checkout.dto.ProductDto;

public interface InvoiceService {

	InvoiceDto getInvoice(String invoiceNo);

	InvoiceDto generateInvoice(List<ProductDto> productDtos, boolean save);
}