package com.eazybytes.cards.service;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.repository.CardsRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CardService {
    @Autowired
    private CardsRepository cardsRepository;

    public List<Cards> getAllCards(){
        return cardsRepository.findAll(); 
        // cardsRepository.findAll().forEach(card -> allCards.add(card));
        // return allCards;
    }
    @Transactional
    public String saveCard(Cards card){

        // need to check the customer exist in the future livrables

        // card.setCreateDt(LocalDate.now());
        cardsRepository.save(card);
        return "saved";
    }

    public List<String> getAllCardsByType(String cardType) {
        List<String> responseList = new ArrayList<>();
        List<Cards> allCards = new ArrayList<Cards>();
        cardsRepository.findAll().forEach(card -> {
            if (card.getCardType().equals(cardType)) {
                allCards.add(card);
            }
        });
        if(!allCards.isEmpty()){
            responseList.add("statue:200");
            responseList.add("données:"+ allCards.toString());
        }else{
            responseList.add("statue:404");
            responseList.add("message: Aucune carte trouvée");
        }
        return responseList;
    }

    public Cards getCardById(int id){
        return cardsRepository.findById(id).get();
    }
    @Transactional
    public String deleteCardAll(){
        List<Cards> allCards = (List<Cards>) cardsRepository.findAll();
        if(allCards != null) {
            cardsRepository.deleteAll();;
            return "All deleted!";
        }else {
            return "card not found!";
        }
    }

    @Transactional
    public String deleteCard(int id){
        Cards cardFind = cardsRepository.findById(id).orElse(null);
        if(cardFind != null) {
            cardsRepository.deleteById(id);
            return "deleted!";
        }else {
            return "card not found!";
        }
    }

    @Transactional
    public String updateCard(int id, Cards updateCard){
        Cards cardFind = cardsRepository.findById(id).orElse(null);
        if(cardFind != null){
            updateCard.setCardId(id);
            updateCard.setCreateDt(LocalDate.now());
            cardsRepository.save(updateCard);
            return "update successful!";
        }else {
            return "card not found!";
        }
    }

    @Transactional
    public List<String> updateCardLimit(int cardId, int totalLimit) {
        List<String> responseList = new ArrayList<>();
        Cards cardFind = cardsRepository.findById(cardId).orElse(null);
        if(totalLimit<0 || totalLimit>100000){
            responseList.add("statue:400");
            responseList.add("message:  Données invalides : "+ totalLimit);
            return responseList;
        }
        if(cardFind != null){
            try {
                cardFind.setTotalLimit(totalLimit);
                cardsRepository.save(cardFind);
                responseList.add("statue:200");
                responseList.add("message: Mise à jour réussie : "+cardId + "; nouvelle limite: "+totalLimit);
            } catch (Exception e) {
                responseList.add("statue:500");
                responseList.add("message: Erreur interne: "+e);
            }
        }else {
            responseList.add("statue:404");
            responseList.add("message: Carte non trouvée: "+ cardId);
        }
        return responseList;
    }

    @Transactional
    public List<String> deleteCardNumber(String number) {
        List<String> responseList = new ArrayList<String>();
        cardsRepository.findAll().forEach(card -> {
            if (card.getCardNumber() !=null && card.getCardNumber().equals(number) ){
                try {
                    cardsRepository.deleteById(card.getCardId());
                    responseList.add("statue:200");
                    responseList.add("message: Succès delete card number: "+number);             
                    
                } catch (Exception e) {
                    responseList.add("statue:500");
                    responseList.add("message: Erreur interne: "+e);       
                }
            }
        });
        if(!responseList.contains("statue:200") && !responseList.contains("statue:500")) {
            responseList.add("statue:404");
            responseList.add("message: Aucune carte trouvée: "+number);
        }
        return responseList;
    }

    @Transactional
    public List<String> deleteCardsByCustomerId(int customerId) {
        List<String> responseList = new ArrayList<String>();

        // if (cardsRepository.findByCustomerDNA(customerId) != null ){
        //     try {
        //         // cardsRepository.findByCard_id(customerId).forEach(card->{
        //         //     cardsRepository.deleteById(card.getCardId());
        //         //     responseList.add("message: Succès delete card, le card id est: "+card.getCardId());             
        //         // });
                
        //     } catch (Exception e) {
        //         responseList.add("statue:500");
        //         responseList.add("message: Erreur interne: "+e);       
        //     }
        // }

        if(!responseList.contains("statue:200") && !responseList.contains("statue:500")) {
            responseList.add("statue:404");
            responseList.add("message: Aucune carte trouvée, customer ID: "+customerId);
        }
        return responseList;
    }

    @Transactional
    public List<String> deleteMultipleCards(List<Integer> ids) {
        List<String> responseList = new ArrayList<String>();
        cardsRepository.findAllById(ids).forEach(card->{
            try {
                cardsRepository.deleteById(card.getCardId());
                if(!responseList.contains("statue:200")){
                    responseList.add("statue:200");
                }
                responseList.add("message: Succès delete card, le card id est: "+card.getCardId());                
            } catch (Exception e) {
                responseList.add("statue:500");
                responseList.add("message: Échec de suppression: "+e);       
            }
        });
        if(!responseList.contains("statue:200") && !responseList.contains("statue:500")) {
            responseList.add("statue:404");
            responseList.add("message: Aucune carte trouvée, card ids: "+ids.toString());
        }
        return responseList;        
    }
}
