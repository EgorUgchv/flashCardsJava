package com.study.cardStudy.mapper;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-10T01:06:09+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto mapToCardDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setDeckId( cardDeckDeckId( card ) );
        cardDto.setRepetitions( toRepetitionsRecord( card.getRepetitions() ) );
        cardDto.setInterval( toIntervalRecord( card.getInterval() ) );
        cardDto.setCardId( card.getCardId() );
        cardDto.setIdInDeck( card.getIdInDeck() );
        cardDto.setTerm( card.getTerm() );
        cardDto.setDefinition( card.getDefinition() );
        cardDto.setNextReview( card.getNextReview() );

        return cardDto;
    }

    @Override
    public Card mapToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setDeck( cardDtoToDeck( cardDto ) );
        card.setRepetitions( fromRepetitionsRecord( cardDto.getRepetitions() ) );
        card.setInterval( fromIntervalRecord( cardDto.getInterval() ) );
        card.setCardId( cardDto.getCardId() );
        card.setIdInDeck( cardDto.getIdInDeck() );
        card.setTerm( cardDto.getTerm() );
        card.setDefinition( cardDto.getDefinition() );
        card.setNextReview( cardDto.getNextReview() );

        return card;
    }

    private Long cardDeckDeckId(Card card) {
        if ( card == null ) {
            return null;
        }
        Deck deck = card.getDeck();
        if ( deck == null ) {
            return null;
        }
        Long deckId = deck.getDeckId();
        if ( deckId == null ) {
            return null;
        }
        return deckId;
    }

    protected Deck cardDtoToDeck(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Deck deck = new Deck();

        deck.setDeckId( cardDto.getDeckId() );

        return deck;
    }
}
