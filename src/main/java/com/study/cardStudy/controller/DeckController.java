package com.study.cardStudy.controller;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.service.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private DeckService deckService;
    @PostMapping
    public ResponseEntity<DeckDto> createDeck(@RequestBody DeckDto deckDto){
        DeckDto savedDeck = deckService.createDeck(deckDto);
        return new ResponseEntity<>(savedDeck, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DeckDto> getDeckById(@PathVariable("id") long deckId){
        DeckDto deckDto = deckService.getDeckById(deckId);
        return ResponseEntity.ok(deckDto);
    }
}
