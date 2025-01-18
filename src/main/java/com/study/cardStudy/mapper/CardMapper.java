package com.study.cardStudy.mapper;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
public class CardMapper {
    public static CardDto mapToCardDto(Card card){
        return new CardDto(
                card.getCardId(),
                card.getTerm(),
                card.getDefinition()
        );
    }
    @Mapper(componentModel = "spring")
    public static Card mapToCard(CardDto cardDto){
        return new Card(
                cardDto.getCardId(),
                cardDto.getTerm(),
                cardDto.getDefinition()
        );
    }
}
*/
@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(source="deck.deckId",target = "deckId")
    CardDto mapToCardDto(Card card);

    @Mapping(source="deckId",target = "deck.deckId")
    Card mapToCard(CardDto cardDto);
}
