/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;

import com.eazybytes.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.repository.CardsRepository;

/**
 * @author Eazy Bytes
 *
 */

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	private CardService cardService;	
	
	@Operation(summary = "get all cards")
	@GetMapping("/AllCards")
	public List<Cards> getAllCards(){
		return cardsRepository.findAll();
	}
	// public ResponseEntity<List<Cards>> getAllCards(){
	// 	return ResponseEntity.ok(cardService.getAllCards());
	// }
	
	@Operation(summary = "create a new card")
	@PostMapping("/newCard")
	public String newCard(@RequestBody Cards card){
		return cardService.saveCard(card);
	}

	@Operation(summary = "get card by id")
	@GetMapping("/myCard/{id}")
	public Cards getCardDetails(@PathVariable("id") int id) {
		return cardService.getCardById(id);
	}
	/*
	@Operation(summary = "get all cards of a certain type")
	@GetMapping("/cardsByType/{cardType}")
	public List<String> getAllCardsByType(@PathVariable("cardType") String cardType){
		return cardService.getAllCardsByType(cardType);
	}


	@Operation(summary = "update card by id")
	@PutMapping("/update/{id}")
	public String updateCard(@PathVariable("id") int id, @RequestBody Cards updateCard) {
		return cardService.updateCard(id, updateCard);
	}

	@Operation(summary = "delete card by id")
	@DeleteMapping("/deleteCard/{id}")
	public String deleteCard(@PathVariable("id") int id){
		return cardService.deleteCard(id);
	}

	@Operation(summary = "delete All cards")
	@DeleteMapping("/deleteCardAll")
	public String deleteCardAll(){
		return cardService.deleteCardAll();
	}

	@Operation(summary = "delete card by number")
	@DeleteMapping("/deleteCardNumber/{number}")
	public List<String> deleteCardNumber(@PathVariable("number") String number){
		return cardService.deleteCardNumber(number);
	}

	@Operation(summary="delete cards by customerId")
	@DeleteMapping("/deleteCardsByCustomerId/{customerId}")
	public List<String> deleteCardsByCustomerId(@PathVariable("customerId") int customerId){
		return cardService.deleteCardsByCustomerId(customerId);
	}

	@Operation(summary="delete multiple cards by Ids")
	@DeleteMapping("/deleteMultipleCards")
	public List<String> deleteMultipleCards(@RequestBody List<Integer> ids){
		return cardService.deleteMultipleCards(ids);
	}

	@PutMapping("/updateCardLimit/{cardId}")
	public List<String> updateCardLimit(@PathVariable("cardId") int cardId, @RequestBody int totalLimit){
		return cardService.updateCardLimit(cardId, totalLimit);
	}
	*/
//	@PostMapping("/myCards")
//	public List<Cards> getCardDetails(@RequestBody Customer customer) {
//		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
//		if (cards != null) {
//			return cards;
//		} else {
//			return null;
//		}
//
//	}

}
