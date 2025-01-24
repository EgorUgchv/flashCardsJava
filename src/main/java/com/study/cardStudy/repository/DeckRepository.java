package com.study.cardStudy.repository;

import com.study.cardStudy.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    Deck findDeckByTitle(String title);
}
