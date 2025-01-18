package com.study.cardStudy.service;

import com.study.cardStudy.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto createCard(CardDto cardDto);
    CardDto getCardById(Long cardId);
    List<CardDto> getAllCards();
    CardDto updateCard(Long cardId,CardDto updatedCard);
    void deleteCard(Long cardId);
}



