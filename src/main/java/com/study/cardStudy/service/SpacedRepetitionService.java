package com.study.cardStudy.service;

import com.study.cardStudy.common.QualityRecord;

import java.time.LocalDateTime;

public interface SpacedRepetitionService {
   LocalDateTime update(QualityRecord qualityRecord, LocalDateTime cardAccessedAt);
}
