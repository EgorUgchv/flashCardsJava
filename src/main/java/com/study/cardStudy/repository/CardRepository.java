package com.study.cardStudy.repository;

import com.study.cardStudy.entity.Card;
import com.study.cardStudy.entity.Deck;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findCardByIdInDeckAndDeck(long IdinDeck, Deck deck);
    @Transactional
    @Modifying
    @Query(value = "INSERT into card (term,definition,id_in_deck,deck_id) " + "VALUES (:#{#card.term},:#{#card.definition},:#{#card.idInDeck},:#{#card.deck.deckId})", nativeQuery = true)
    int insertCard(@Param("card") Card card);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Card SET term = :#{#card.term}, definition = :#{#card.definition} WHERE (deck_id = :#{#card.deck.deckId} AND id_in_deck =:#{#card.idInDeck})", nativeQuery = true)
    void updateCard(@Param("card") Card card);

    @Transactional
    @Modifying
    @Query(value = "DELETE  FROM Card WHERE (deck_id = :#{#card.deck.deckId} AND id_in_deck =:#{#card.idInDeck})", nativeQuery = true)
    void deleteCard(@Param("card") Card card);
}
