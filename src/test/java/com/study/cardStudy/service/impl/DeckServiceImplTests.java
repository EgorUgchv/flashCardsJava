package com.study.cardStudy.service.impl;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

/*
@JdbcTest
@Import(DeckServiceImpl.class)
*/
@SpringBootTest
@Import(DeckServiceImpl.class)
class DeckServiceImplTests {
    @Autowired
    private DeckServiceImpl deckService;

/*

    @Autowired
    DeckRepository deckRepository;

*/


    @Test
    void shouldReturnSavedDeckDtoAndCreateDeck() throws Exception {
        List<CardDto> cardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L), new CardDto(2L, 2L, "dig", "копать", 1L), new CardDto(3L, 3L, "cut", "резать", 1L));
        DeckDto deckDto = new DeckDto(1L, "english", cardDtoList);
        DeckDto savedDeckDto = deckService.createDeck(deckDto);
    }

}
