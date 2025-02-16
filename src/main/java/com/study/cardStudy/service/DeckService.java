package com.study.cardStudy.service;

import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.dto.DeckDto;

import java.time.LocalDateTime;

public interface DeckService {
    DeckDto createDeck(DeckDto deckDto);

    DeckDto getDeckById(String title);
    CardDto updateQuality(long deckId, long idInDeck, QualityRecord cardQuality, LocalDateTime time);
}
