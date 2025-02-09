package com.study.cardStudy.service;

import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;

public interface DeckService {
    DeckDto createDeck(DeckDto deckDto);

    DeckDto getDeckById(String title);
    CardDto updateQuality(QualityRecord cardQuality,long deckId,long idInDeck);
}
