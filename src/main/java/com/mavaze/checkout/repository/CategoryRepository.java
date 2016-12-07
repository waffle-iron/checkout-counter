package com.mavaze.checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavaze.checkout.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
