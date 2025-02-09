package com.study.cardStudy.dto;

import com.study.cardStudy.common.EasinessRecord;
import com.study.cardStudy.common.IntervalRecord;
import com.study.cardStudy.common.RepetitionsRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private RepetitionsRecord repetitions;
    private IntervalRecord interval;
    private EasinessRecord easinessRecord;
    private LocalDateTime nextReview;
}
