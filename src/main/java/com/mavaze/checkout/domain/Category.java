package com.mavaze.checkout.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = -5614567858340252914L;

	@Id
	@GeneratedValue
	@Column(name="ID")
	private long categoryId;
	
	@Column(name="NAME", length=100, unique=true)
	private String categoryName;
		
	@Column(name="DISCOUNT")
	private int discount;
	
	@Column(name="DESCRIPTION", length=1000, nullable=true)
	private String description;
	
	@OneToMany(mappedBy = "category")
	private Set<Product> products;

}
