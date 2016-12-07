package com.mavaze.checkout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavaze.checkout.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

	List<Invoice> findByInvoiceNo(String invoiceNo);
}
