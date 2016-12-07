package com.mavaze.checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mavaze.checkout.domain.Actor;

@Repository
public interface UserRepository extends JpaRepository<Actor, Long>{

	Actor findByUserName(String userName);
}