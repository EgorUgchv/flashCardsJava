package com.study.cardStudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;
    @Column(name = "id_in_deck")
    private Long idInDeck;
    @Column(name = "term")
    private String term;
    @Column(name = "definition")
    private String definition;
    @Column(name = "repetitions")
    private int repetitions = 0;
    @Column(name = "interval")
    private int interval = 1;
    @Column(name = "easiness")
    private double easiness = 2.5;
    @Column(name = "next_review")
    private LocalDateTime nextReview;
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
}
