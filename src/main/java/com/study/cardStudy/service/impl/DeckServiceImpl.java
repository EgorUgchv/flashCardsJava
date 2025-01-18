package com.study.cardStudy.service.impl;

import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import com.study.cardStudy.mapper.DeckMapper;
import com.study.cardStudy.repository.CardRepository;
import com.study.cardStudy.repository.DeckRepository;
import com.study.cardStudy.service.DeckService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class DeckServiceImpl implements DeckService {
    public DeckRepository deckRepository;
    public CardRepository cardRepository;
    public final DeckMapper deckMapper;


    @Override
    @Transactional
    public DeckDto createDeck(DeckDto deckDto) {
        Deck deck = deckMapper.mapToDeck(deckDto);
        deck.getCardList().forEach(card -> card.setDeck(deck));

        Deck existingDeck = deckRepository.findDeckByTitle(deck.getTitle());
        if (existingDeck != null) {
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

            int sizeNewDeck = deck.getCardList().size();
            int sizeOldDeck = existingDeck.getCardList().size();
            boolean isNewDeckSmallerThanExisting = sizeNewDeck < sizeOldDeck;

            if (isNewDeckSmallerThanExisting) {
                Iterator<Card> cardListIterator = existingDeck.getCardList().listIterator(sizeOldDeck - sizeNewDeck);
                while (cardListIterator.hasNext()) {
                    Card currentCard = cardListIterator.next();
                    cardRepository.deleteCard(currentCard);
                }
                Card currentCard = cardListIterator.next();
                cardRepository.deleteCard(currentCard);
            }
        } else {
            deckRepository.save(deck);
        }

        DeckDto savedDeckDto = deckMapper.mapToDeckDto(deck);
        savedDeckDto.getCardList().forEach(cardDto -> cardDto.setDeckId(deck.getDeckId()));

        return savedDeckDto;
    }

    @Override
    public DeckDto getDeckById(Long deckId) {
/*
        Deck deck = deckRepository.findById(deckId).orElseThrow(() -> new ResourceNotFoundException("Deck is not exist with given id: " + deckId));
*/
        Deck deck = deckRepository.findDeckByTitle("sdtrr");
        if (deckRepository.findDeckByTitle("sdf") != null) {

            System.out.println("asdasf");
            System.out.println();
        }
        return deckMapper.mapToDeckDto(deck);
    }


}
