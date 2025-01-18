package com.study.cardStudy.controller;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/cards")
public class CardController {
    private CardService cardService;
    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto){
        CardDto savedCard = cardService.createCard(cardDto);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable("id") long cardId){
        CardDto cardDto = cardService.getCardById(cardId);
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards(){
        List<CardDto> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @PutMapping("{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable("id") Long cardId,
                                              @RequestBody CardDto updatedCard){
        CardDto cardDto = cardService.updateCard(cardId, updatedCard);
        return ResponseEntity.ok(cardDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") long cardId){
       cardService.deleteCard(cardId);
       return ResponseEntity.ok("Card deleted successfully!");
    }
}
