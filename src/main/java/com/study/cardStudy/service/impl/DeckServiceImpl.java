package com.study.cardStudy.service.impl;

import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import com.study.cardStudy.mapper.CardMapper;
import com.study.cardStudy.mapper.DeckMapper;
import com.study.cardStudy.repository.CardRepository;
import com.study.cardStudy.repository.DeckRepository;
import com.study.cardStudy.service.DeckService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class DeckServiceImpl implements DeckService {
    private final CardMapper cardMapper;
    private DeckRepository deckRepository;
    private CardRepository cardRepository;
    private SpacedRepetitionServiceImpl repetitionService;
    private final DeckMapper deckMapper;


    @Override
    public DeckDto createDeck(DeckDto deckDto) {

        Deck deck = deckMapper.mapToDeck(deckDto);
        deck.getCardList().forEach(card -> card.setDeck(deck));

        String deckTitle = deck.getTitle();
        Deck existingDeck = deckRepository.findDeckByTitle(deckTitle);
        if (existingDeck != null) {
            updateExistingDeck(existingDeck, deck);
        } else {
            deckRepository.save(deck);
        }

        DeckDto savedDeckDto = deckMapper.mapToDeckDto(deck);
        savedDeckDto.getCardList().forEach(cardDto -> cardDto.setDeckId(deck.getDeckId()));

        return savedDeckDto;
    }

    private void updateExistingDeck(Deck existingDeck, Deck deck) {
        existingDeck.getCardList().sort(Comparator.comparing(Card::getIdInDeck));
        long deckId = existingDeck.getDeckId();
        deck.getCardList().forEach(card -> {
            card.getDeck().setDeckId(deckId);
            Card existingCard = cardRepository.findCardByIdInDeckAndDeck(card.getIdInDeck(), existingDeck);
            if (existingCard != null) {
                cardRepository.updateCard(card);
            } else {
                cardRepository.insertCard(card);
            }
        });

        deleteExtraCards(existingDeck, deck);
    }

    private void deleteExtraCards(Deck existingDeck, Deck newDeck) {
        int sizeNewDeck = newDeck.getCardList().size();
        int sizeOldDeck = existingDeck.getCardList().size();
        boolean isNewDeckSmallerThanExisting = sizeNewDeck < sizeOldDeck;

        if (isNewDeckSmallerThanExisting) {
            Iterator<Card> cardListIterator = existingDeck.getCardList().listIterator(sizeOldDeck - sizeNewDeck-1);
            Card currentCard = null;
            while (cardListIterator.hasNext()) {
                currentCard = cardListIterator.next();
                cardRepository.deleteCard(currentCard);
            }

            cardRepository.deleteCard(currentCard);
        }
    }

    @Override
    public DeckDto getDeckById(String title) {
        Deck deck = deckRepository.findDeckByTitle(title);
        return deckMapper.mapToDeckDto(deck);
    }

    @Override
    public CardDto updateNextReviewCard(long deckId, long idInDeck, QualityRecord cardQuality, LocalDateTime cardAccessedAt) {
        Card cardToUpdateQuality = cardRepository.findByIdInDeckAndDeck_DeckId(idInDeck, deckId);
        LocalDateTime nextReview = repetitionService.update(cardQuality, cardAccessedAt);
        cardRepository.updateNextReview(deckId,idInDeck,nextReview);
        return cardMapper.mapToCardDto(cardToUpdateQuality);

    }
}
