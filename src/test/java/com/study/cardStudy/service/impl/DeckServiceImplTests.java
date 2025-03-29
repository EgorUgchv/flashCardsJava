package com.study.cardStudy.service.impl;

import com.study.cardStudy.common.EasinessRecord;
import com.study.cardStudy.common.IntervalRecord;
import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.common.RepetitionsRecord;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@ActiveProfiles("tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DeckServiceImplTests {
    @Autowired
    private DeckServiceImpl deckService;
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    @BeforeAll
    static void startServers() throws Exception {
        // Start H2 servers
        Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        Server tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    }

    @Test
    @DisplayName("Create Deck in Database")
    void shouldCreateDeck() throws Exception {
        RepetitionsRecord repetitions = new RepetitionsRecord(0);
        IntervalRecord interval = new IntervalRecord(1);
        EasinessRecord easiness = new EasinessRecord(2.5);
        String cardAccessedAtStr = "2025-05-10 15:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime cardAccessedAt = LocalDateTime.parse(cardAccessedAtStr, formatter);

        List<CardDto> cardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L, repetitions, interval, easiness, cardAccessedAt), new CardDto(2L, 2L, "dig", "копать", 1L, repetitions, interval, easiness, cardAccessedAt), new CardDto(3L, 3L, "cut", "резать", 1L, repetitions, interval, easiness, cardAccessedAt));
        DeckDto deckDto = new DeckDto(1L, "english", cardDtoList);

        deckService.createDeck(deckDto);

        Deck savedDeck = deckRepository.findById(deckDto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found"));
        cardDtoList.forEach(card -> {
            CardDto cardFromDB = cardMapper.mapToCardDto(cardRepository.findCardByIdInDeckAndDeck(card.idInDeck, savedDeck));
            assertAll("Grouped Assertions of Deck from DB", () -> assertEquals(card.getCardId(), cardFromDB.getCardId()), () -> assertEquals(card.getIdInDeck(), cardFromDB.getIdInDeck()), () -> assertEquals(card.getTerm(), cardFromDB.getTerm()), () -> assertEquals(card.getDefinition(), cardFromDB.getDefinition()), () -> assertEquals(card.getDeckId(), cardFromDB.getDeckId())

            );

        });
    }

    @Test
    @DisplayName("Update deck in database")
    void shouldUpdateDeck() throws Exception {
        RepetitionsRecord repetitions = new RepetitionsRecord(0);
        IntervalRecord interval = new IntervalRecord(1);
        EasinessRecord easiness = new EasinessRecord(2.5);
        String cardAccessedAtStr = "2025-05-10 15:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime cardAccessedAt = LocalDateTime.parse(cardAccessedAtStr, formatter);

        List<CardDto> cardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L, repetitions, interval, easiness, cardAccessedAt), new CardDto(2L, 2L, "dig", "копать", 1L, repetitions, interval, easiness, cardAccessedAt), new CardDto(3L, 3L, "cut", "резать", 1L, repetitions, interval, easiness, cardAccessedAt));
        DeckDto deckDto = new DeckDto(1L, "english", cardDtoList);
        deckService.createDeck(deckDto);

        List<CardDto> newCardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L, repetitions, interval, easiness, cardAccessedAt));
        DeckDto newDeckDto = new DeckDto(1L, "english", newCardDtoList);
        deckService.createDeck(newDeckDto);

        Deck savedDeck = deckRepository.findById(newDeckDto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found"));
        newCardDtoList.forEach(card -> {
            CardDto cardFromDB = cardMapper.mapToCardDto(cardRepository.findCardByIdInDeckAndDeck(card.idInDeck, savedDeck));
            assertAll("Grouped Assertions of  Deck from DB", () -> assertEquals(card.getCardId(), cardFromDB.getCardId()), () -> assertEquals(card.getIdInDeck(), cardFromDB.getIdInDeck()), () -> assertEquals(card.getTerm(), cardFromDB.getTerm()), () -> assertEquals(card.getDefinition(), cardFromDB.getDefinition()), () -> assertEquals(card.getDeckId(), cardFromDB.getDeckId()));
        });
    }

    @Test
    @DisplayName("Card update in the next review")
    void shouldUpdateCardNextReview() throws Exception {
        RepetitionsRecord repetitions = new RepetitionsRecord(0);
        IntervalRecord interval = new IntervalRecord(1);
        EasinessRecord easiness = new EasinessRecord(2.5);

        List<CardDto> oldCardDtoList = List.of(new CardDto(1L, 1L, "put", "класть", 1L, repetitions, interval, easiness, null));
        DeckDto oldDeckDto = new DeckDto(1L, "english", oldCardDtoList);
        deckService.createDeck(oldDeckDto);

        String cardAccessedAtStr = "2025-05-10 15:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime cardAccessedAt = LocalDateTime.parse(cardAccessedAtStr, formatter);
        int hardQuality = 1;
        QualityRecord cardQuality = new QualityRecord(hardQuality);


        deckService.updateNextReviewCard(1L, 1L, cardQuality, cardAccessedAt);
        LocalDateTime expectedNextReview = cardAccessedAt.plusDays(1);
        Deck savedDeck = deckRepository.findById(oldDeckDto.getDeckId()).orElseThrow(() -> new RuntimeException("Deck not found"));
        oldCardDtoList.forEach(card -> {
            CardDto cardFromDB = cardMapper.mapToCardDto(cardRepository.findCardByIdInDeckAndDeck(card.idInDeck, savedDeck));
            System.out.println("REVIEW:" + cardFromDB.getNextReview());
            System.out.println("REVIEW EQUAL:" + cardAccessedAt);

            assertAll("Grouped Assertions of  Deck from DB", () -> assertEquals(card.getCardId(), cardFromDB.getCardId()), () -> assertEquals(card.getIdInDeck(), cardFromDB.getIdInDeck()), () -> assertEquals(card.getTerm(), cardFromDB.getTerm()), () -> assertEquals(card.getDefinition(), cardFromDB.getDefinition()), () -> assertEquals(card.getDeckId(), cardFromDB.getDeckId()), () -> assertEquals(expectedNextReview, cardFromDB.getNextReview()));
        });
    }
}
