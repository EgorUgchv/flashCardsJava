package com.study.cardStudy.controller;

import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.service.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    @PatchMapping("/{deckId}/cards/{idInDeck}")
    public ResponseEntity<CardDto> updateCardQuality(
            @RequestBody Map<String,Object> body,
            @PathVariable("deckId") long deckId,
            @PathVariable("idInDeck") long idInDeck
            ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse((String) body.get("time"), formatter);
        QualityRecord qualityRecord = new QualityRecord((Integer) body.get("quality"));
        System.out.println("TIME" + time);
        System.out.println("Quality" + qualityRecord);
       CardDto savedCard = deckService.updateNextReviewCard(deckId, idInDeck, qualityRecord, time);
       return new ResponseEntity<>(savedCard, HttpStatus.ACCEPTED);
    }
}
