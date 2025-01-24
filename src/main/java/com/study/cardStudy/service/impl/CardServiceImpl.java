package com.study.cardStudy.service.impl;

import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.entity.Card;
import com.study.cardStudy.exception.ResourceNotFoundException;
import com.study.cardStudy.mapper.CardMapper;
import com.study.cardStudy.repository.CardRepository;
import com.study.cardStudy.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;
    private CardMapper cardMapper;

    @Override
    public CardDto createCard(CardDto cardDto) {
        Card card = cardMapper.mapToCard(cardDto);
        Card savedCard = cardRepository.save(card);
        return cardMapper.mapToCardDto(savedCard);
    }

    @Override
    public CardDto getCardById(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card is not exist with given id: " + cardId));
        return cardMapper.mapToCardDto(card);
    }

    @Override
    public List<CardDto> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream().map((card) -> cardMapper.mapToCardDto(card))
                .collect(Collectors.toList());
    }

    @Override
    public CardDto updateCard(Long cardId, CardDto updatedCard) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card is not exist with given id:" + cardId)
        );

        card.setTerm(updatedCard.getTerm());
        card.setDefinition(updatedCard.getDefinition());
        Card updatedCardObj = cardRepository.save(card);
        return cardMapper.mapToCardDto(updatedCardObj);
    }

    @Override
    public void deleteCard(Long cardId) {
        cardRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card is not exist with given id:" + cardId)
        );
        cardRepository.deleteById(cardId);
    }
}
