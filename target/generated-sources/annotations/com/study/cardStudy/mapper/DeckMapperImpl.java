package com.study.cardStudy.mapper;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-17T02:06:36+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class DeckMapperImpl implements DeckMapper {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public DeckDto mapToDeckDto(Deck deck) {
        if ( deck == null ) {
            return null;
        }

        DeckDto deckDto = new DeckDto();

        deckDto.setDeckId( deck.getDeckId() );
        deckDto.setTitle( deck.getTitle() );
        deckDto.setCardList( cardListToCardDtoList( deck.getCardList() ) );

        return deckDto;
    }

    @Override
    public Deck mapToDeck(DeckDto deckDto) {
        if ( deckDto == null ) {
            return null;
        }

        Deck deck = new Deck();

        deck.setDeckId( deckDto.getDeckId() );
        deck.setTitle( deckDto.getTitle() );
        deck.setCardList( cardDtoListToCardList( deckDto.getCardList() ) );

        return deck;
    }

    protected List<CardDto> cardListToCardDtoList(List<Card> list) {
        if ( list == null ) {
            return null;
        }

        List<CardDto> list1 = new ArrayList<CardDto>( list.size() );
        for ( Card card : list ) {
            list1.add( cardMapper.mapToCardDto( card ) );
        }

        return list1;
    }

    protected List<Card> cardDtoListToCardList(List<CardDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Card> list1 = new ArrayList<Card>( list.size() );
        for ( CardDto cardDto : list ) {
            list1.add( cardMapper.mapToCard( cardDto ) );
        }

        return list1;
    }
}
