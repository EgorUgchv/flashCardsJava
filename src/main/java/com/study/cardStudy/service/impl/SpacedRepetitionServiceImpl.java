package com.study.cardStudy.service.impl;

import com.study.cardStudy.common.QualityRecord;
import com.study.cardStudy.service.SpacedRepetitionService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SpacedRepetitionServiceImpl implements SpacedRepetitionService {
    private int repetitions;
    private int interval;
    private double easiness;
    private LocalDateTime nextReview;

    public SpacedRepetitionServiceImpl() {
        this.repetitions = 0;
        this.interval = 1;
        this.easiness = 2.5;
        this.nextReview = null;

    }

    public LocalDateTime update(QualityRecord qualityRecord, LocalDateTime cardAccessedAt) {

        if (qualityRecord.quality() == 0) {
            repetitions = 0;
            interval = 0;
        } else {
            easiness += 0.1 - (3 - qualityRecord.quality()) * (0.08 + (3 - qualityRecord.quality()) * 0.02);
            easiness = Math.max(1.3, easiness);

            repetitions++;

            if (repetitions == 1) {
                interval = 1;
            } else if (repetitions == 2) {
                interval = 6;
            } else {
                interval = (int) Math.round(interval * easiness);
            }
        }
        return cardAccessedAt.plusDays(interval);
    }
}
