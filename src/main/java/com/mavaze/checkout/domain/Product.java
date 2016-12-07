package com.mavaze.checkout.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = -6993523519027692151L;

	@Id
	@Column(name="ID")
	@GeneratedValue
	private long productId;
	
	@Column(name="NAME", length=100, nullable=false)
	private String productName;
	
	@Column(name="BARCODE", unique=true, nullable=false)
	private String barcode;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY", nullable=true)
	private Category category;
	
	@Column(name="PRICE", precision=2, nullable=false)
	private double price;
	
	@Column(name="DESCRIPTION", length=1000, nullable=true)
	private String description;

}
