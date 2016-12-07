package com.mavaze.checkout.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.mavaze.checkout.domain.Category;
import com.mavaze.checkout.domain.Product;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ProductRepositoryTest {
	
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void testFindProductsByCategoryNameReturnsProductWithGivenCategory() {
    	
    	Product p1 = new Product();
    	p1.setProductName("TP1");
    	p1.setBarcode("ABC");
    	
    	Product p2 = new Product();
    	p2.setProductName("TP2");
    	p2.setBarcode("XYZ");
    	
    	Category c1 = new Category();
    	c1.setCategoryName("TC1");
    	p1.setCategory(c1);
    	
    	this.entityManager.persist(c1);
        this.entityManager.persist(p1);
        this.entityManager.persist(p2);
        
        List<Product> products = this.productRepository.findByCategoryName(new String[]{"TC1", "TC2"});
        
        assertThat(products.get(0).getProductName()).isEqualTo("TP1");
    }

    @Test(expected=DataIntegrityViolationException.class)
    public void testProductsWithSameBarcodeThrowsException() throws Exception {
    	
    	Product p1 = new Product();
    	p1.setProductName("P1");
    	p1.setBarcode("ABC");
    	this.productRepository.save(p1);
    	
    	Product p2 = new Product();
    	p2.setProductName("P2");
    	p2.setBarcode("ABC");
    	this.productRepository.save(p2);
    	
    	this.productRepository.findAll();
    	
    }
    
}
