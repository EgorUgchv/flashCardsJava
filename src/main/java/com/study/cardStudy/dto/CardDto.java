package com.study.cardStudy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    public Long cardId;
    public Long idInDeck;
    public String term;
    public String definition;
    public Long deckId;
}
