package com.study.cardStudy.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = IntervalRecordSerializer.class)
public record IntervalRecord(int interval) {

    public IntervalRecord{
        if(interval<0){
            throw new IllegalArgumentException("Interval must be greater than 0.");
        }
    }
}
