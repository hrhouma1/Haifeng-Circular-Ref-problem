package com.eazybytes.cards.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eazybytes.cards.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    
} 