package com.tertal.konpeito.service.impl;

import org.springframework.stereotype.Service;

import com.tertal.konpeito.dto.StatisticsDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.repository.ApplicationRepository;
import com.tertal.konpeito.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ApplicationRepository repo;

    public StatisticsServiceImpl(ApplicationRepository repo) {
        this.repo = repo;
    }

    @Override
    public StatisticsDto getStatistics() {
        long totalApplication = this.repo.count();
        long offers = this.repo.countByStatus(Application.Status.OFFERED);
        long interviews = this.repo.countByStatus(Application.Status.INTERVIEW);
        long rejections = this.repo.countByStatus(Application.Status.REJECTED);

        return new StatisticsDto(
                totalApplication,
                offers,
                interviews,
                rejections
        );
    }

}
