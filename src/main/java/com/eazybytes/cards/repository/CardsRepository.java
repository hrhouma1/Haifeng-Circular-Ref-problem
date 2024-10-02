package com.eazybytes.cards.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.cards.model.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer> {

    // List<Cards> findByCustomerDNA(int customerId);
}
