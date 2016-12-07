package com.mavaze.checkout.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = -4360095605856792748L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long invoiceId;
	
	@Column(name="INVOICE_NO", nullable=false)
	private String invoiceNo;
	
	@Column(name="PRODUCT_ID", nullable=false)
	private Product product;
	
	@Column(name="PRICE", nullable=false)
	private double price;
	
	@Column(name="DISCOUNT", nullable=true)
	private int discount;
	
	@CreatedDate
	@Column(name="TX_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	
	@CreatedBy
	@OneToOne
	@JoinColumn(name="CASHIER_ID")
	private Actor cashier;
	
}
