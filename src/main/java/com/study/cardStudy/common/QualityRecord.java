package com.study.cardStudy.common;

public record QualityRecord(int quality) {
    public QualityRecord {
        if (quality < 0 || quality > 3) {
            throw new IllegalArgumentException("Quality " + quality + "doesn't exist");
        }
    }
}
