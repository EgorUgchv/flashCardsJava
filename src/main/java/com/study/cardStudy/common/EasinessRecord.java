package com.study.cardStudy.common;

public record EasinessRecord(double easiness) {
    public EasinessRecord {
        if(easiness < 1.3 || easiness > 2.5){
            throw new IllegalArgumentException("Easiness must be between 1.3 and 2.5 inclusive.");
        }
    }
}
