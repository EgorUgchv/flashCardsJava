package com.study.cardStudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
}
