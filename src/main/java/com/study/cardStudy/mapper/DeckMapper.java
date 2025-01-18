package com.study.cardStudy.mapper;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {CardMapper.class})
public interface DeckMapper {
    DeckDto mapToDeckDto(Deck deck);
    Deck mapToDeck(DeckDto deckDto);
}
