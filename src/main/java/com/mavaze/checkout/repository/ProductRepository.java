package com.mavaze.checkout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mavaze.checkout.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByBarcode(String barcode);
	
	@Query("select p from Product p where p.category.categoryName in ?1")
	List<Product> findByCategoryName(String[] categories);
	
}
