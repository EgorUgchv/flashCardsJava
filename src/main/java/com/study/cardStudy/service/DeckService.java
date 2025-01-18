package com.study.cardStudy.service;

import com.study.cardStudy.dto.DeckDto;

public interface DeckService {
    DeckDto createDeck(DeckDto deckDto);

    DeckDto getDeckById(Long deckId);

}
