package com.study.cardStudy.common;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = RepetitionsRecordSerializer.class)
public record RepetitionsRecord(int repetitions) {
   public RepetitionsRecord{
       if(repetitions<0){
           throw new IllegalArgumentException("Repetitions must be greater than 0.");
       }
   }
}
