package com.study.cardStudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deck")
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id")
    private Long deckId;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Card> cardList;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || o.getClass() != this.getClass()) return false;
//
//        Deck deck = (Deck) o;
//        return deckId == deck.deckId
//                && (title == deck.title
//                || (title != null && title.equals(deck.getTitle()))) && (cardList == deck.cardList
//                || (cardList != null && cardList.equals(deck.getCardList())
//        ));
//    }
}
