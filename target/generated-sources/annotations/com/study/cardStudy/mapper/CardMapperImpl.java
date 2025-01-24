package com.study.cardStudy.mapper;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-21T21:43:27+0700",
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
        cardDto.setCardId( card.getCardId() );
        cardDto.setIdInDeck( card.getIdInDeck() );
        cardDto.setTerm( card.getTerm() );
        cardDto.setDefinition( card.getDefinition() );

        return cardDto;
    }

    @Override
    public Card mapToCard(CardDto cardDto) {
        if ( cardDto == null ) {
            return null;
        }

        Card card = new Card();

        card.setDeck( cardDtoToDeck( cardDto ) );
        card.setCardId( cardDto.getCardId() );
        card.setIdInDeck( cardDto.getIdInDeck() );
        card.setTerm( cardDto.getTerm() );
        card.setDefinition( cardDto.getDefinition() );

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
