package com.study.cardStudy.controller;

import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.service.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.badRequest;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private DeckService deckService;

    @PostMapping
    public ResponseEntity<DeckDto> createDeck(@RequestBody DeckDto deckDto) {
        DeckDto savedDeck = deckService.createDeck(deckDto);
        return new ResponseEntity<>(savedDeck, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<DeckDto> getDeckByTitle(@RequestParam String title) {
        DeckDto deckDto = deckService.getDeckById(title);
        if(deckDto == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deckDto,HttpStatus.ACCEPTED);
    }
}
