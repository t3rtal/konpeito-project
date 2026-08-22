package com.tertal.konpeito.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tertal.konpeito.config.TenantContext;
import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.entity.User;
import com.tertal.konpeito.repository.ApplicationRepository;
import com.tertal.konpeito.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock
    private ApplicationRepository repo;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ApplicationServiceImpl service;

    @Test
    void addApplicationShouldSucceed() {
        ApplicationDto application = new ApplicationDto();
        application.setCompany("Tertal");
        TenantContext.setCurrentTenant(1L);
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(java.util.Optional.of(User.builder().id(1L).build()));

        Mockito.when(this.repo.save(Mockito.any(Application.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        try {
            ApplicationDto addedApplication = this.service.addApplication(application);
            assertEquals("Tertal", addedApplication.getCompany());
        } finally {
            TenantContext.clear();
        }
    }

}