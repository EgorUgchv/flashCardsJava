package com.study.cardStudy.service.impl;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.entity.Deck;
import com.study.cardStudy.mapper.CardMapper;
import com.study.cardStudy.repository.CardRepository;
import com.study.cardStudy.repository.DeckRepository;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureCache
@AutoConfigureTestEntityManager
@Import(DeckServiceImpl.class)
/*
@TestPropertySource(locations = "../resources/application.properties")
*/
@ActiveProfiles("tests")
class DeckServiceImplTests {
    @Autowired
    private DeckServiceImpl deckService;
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;
    private static Server webServer;
    private static Server tcpServer;
@BeforeAll
static void startServers() throws Exception {
    // Start H2 servers
    webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
    tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
}
    @Test
    @DisplayName("Create Deck in Database")
    void shouldCreateDeck() throws Exception {
        List<CardDto> cardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L), new CardDto(2L, 2L, "dig", "копать", 1L), new CardDto(3L, 3L, "cut", "резать", 1L));
        DeckDto deckDto = new DeckDto(1L, "english", cardDtoList);

        deckService.createDeck(deckDto);

        Deck savedDeck = deckRepository.findById(deckDto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found"));
        cardDtoList.forEach(card -> {
            CardDto cardFromDB = cardMapper.mapToCardDto(cardRepository.findCardByIdInDeckAndDeck(card.idInDeck, savedDeck));
            assertAll("Grouped Assertions of Deck from DB",
                    () -> assertEquals(card.getCardId(), cardFromDB.getCardId()),
                    () -> assertEquals(card.getIdInDeck(), cardFromDB.getIdInDeck()),
                    () -> assertEquals(card.getTerm(), cardFromDB.getTerm()),
                    () -> assertEquals(card.getDefinition(), cardFromDB.getDefinition()),
                    () -> assertEquals(card.getDeckId(), cardFromDB.getDeckId())

            );

        });
    }

    @Test
    @DisplayName("Update deck in database")
    void shouldUpdateDeck() throws Exception {
        List<CardDto> cardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L), new CardDto(2L, 2L, "dig", "копать", 1L), new CardDto(3L, 3L, "cut", "резать", 1L));
        DeckDto deckDto = new DeckDto(1L, "english", cardDtoList);
        deckService.createDeck(deckDto);

        List<CardDto> newCardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L));
        DeckDto newDeckDto = new DeckDto(1L, "english", cardDtoList);
        deckService.createDeck(newDeckDto);

        Deck savedDeck = deckRepository.findById(newDeckDto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found"));
        cardDtoList.forEach(card -> {
            CardDto cardFromDB = cardMapper.mapToCardDto(cardRepository.findCardByIdInDeckAndDeck(card.idInDeck, savedDeck));
            assertAll("Grouped Assertions of  Deck from DB",
                    () -> assertEquals(card.getCardId(), cardFromDB.getCardId()),
                    () -> assertEquals(card.getIdInDeck(), cardFromDB.getIdInDeck()),
                    () -> assertEquals(card.getTerm(), cardFromDB.getTerm()),
                    () -> assertEquals(card.getDefinition(), cardFromDB.getDefinition()),
                    () -> assertEquals(card.getDeckId(), cardFromDB.getDeckId())

            );

        });
    }

}
