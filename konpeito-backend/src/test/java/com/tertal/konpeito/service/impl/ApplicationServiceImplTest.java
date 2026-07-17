package com.tertal.konpeito.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.repository.ApplicationRepository;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository repo;

    @InjectMocks
    private ApplicationServiceImpl service;

    @Test
    void addApplicationShouldSucceed() {
        ApplicationDto application = new ApplicationDto();
        application.setCompany("Tertal");

        Mockito.when(this.repo.save(Mockito.any(Application.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        ApplicationDto addedApplication = this.service.addApplication(application);

        assertEquals("Tertal", addedApplication.getCompany());
    }

}