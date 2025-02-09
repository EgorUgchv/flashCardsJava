package com.study.cardStudy.mapper;

import com.study.cardStudy.common.IntervalRecord;
import com.study.cardStudy.common.RepetitionsRecord;
import com.study.cardStudy.dto.CardDto;
import com.study.cardStudy.entity.Card;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/*
public class CardMapper {
    public static CardDto mapToCardDto(Card card){
        return new CardDto(
                card.getCardId(),
                card.getTerm(),
                card.getDefinition()
        );
    }
    @Mapper(componentModel = "spring")
    public static Card mapToCard(CardDto cardDto){
        return new Card(
                cardDto.getCardId(),
                cardDto.getTerm(),
                cardDto.getDefinition()
        );
    }
}
*/
@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(source="deck.deckId",target = "deckId")
    @Mapping(source = "repetitions", target = "repetitions", qualifiedByName = "toRepetitionsRecord")
    @Mapping(source = "interval", target = "interval", qualifiedByName = "toIntervalRecord")
    CardDto mapToCardDto(Card card);
    @Mapping(source="deckId",target = "deck.deckId")
    @Mapping(source = "repetitions", target = "repetitions", qualifiedByName = "fromRepetitionsRecord")
    @Mapping(source = "interval", target = "interval", qualifiedByName = "fromIntervalRecord")
    Card mapToCard(CardDto cardDto);

@Named("toRepetitionsRecord")
default RepetitionsRecord toRepetitionsRecord(int repetitions) {
    return new RepetitionsRecord(repetitions);
}

        @Named("fromRepetitionsRecord")
        default int fromRepetitionsRecord(RepetitionsRecord record) {
            return record != null ? record.repetitions() : 0;
        }
 @Named("toIntervalRecord")
    default IntervalRecord toIntervalRecord(int interval) {
        return new IntervalRecord(interval);
    }

    @Named("fromIntervalRecord")
    default int fromIntervalRecord(IntervalRecord record) {
        return record != null ? record.interval() : 1;
    }
}
