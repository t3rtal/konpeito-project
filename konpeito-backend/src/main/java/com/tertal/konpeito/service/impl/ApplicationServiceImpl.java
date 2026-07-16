package com.tertal.konpeito.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.dto.ApplicationFilterDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.exception.ResourceNotFoundException;
import com.tertal.konpeito.mapper.ApplicationMapper;
import com.tertal.konpeito.repository.ApplicationRepository;
import com.tertal.konpeito.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationRepository repo;

    @Override
    public List<ApplicationDto> getApplications(ApplicationFilterDto filter) {
        List<Application> applications = this.repo.findApplications(
                filter.getPosition(), filter.getStatus());

        return applications.stream()
                .map(ApplicationMapper::mapToApplicationDto)
                .toList();
    }

    @Override
    public ApplicationDto getApplication(Long applicationId) {
        Application application = this.repo.findById(applicationId).orElseThrow(
                () -> new ResourceNotFoundException("There is no application with id: " + applicationId)
        );
        return ApplicationMapper.mapToApplicationDto(application);
    }

    @Override
    public ApplicationDto addApplication(ApplicationDto applicationDto) {
        Application application = ApplicationMapper.mapToApplication(applicationDto);
        Application savedApplication = this.repo.save(application);
        return ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    public ApplicationDto updateApplication(
            Long applicationId, ApplicationDto applicationDto
    ) {
        Application application = this.repo.findById(applicationId).orElseThrow(
                () -> new ResourceNotFoundException("There is no application with id: " + applicationId)
        );

        application.setCompany(applicationDto.getCompany());
        application.setPosition(applicationDto.getPosition());
        application.setStatus(applicationDto.getStatus());
        application.setSalary(applicationDto.getSalary());
        application.setJobUrl(applicationDto.getJobUrl());
        application.setDate(applicationDto.getDate());

        Application savedApplication = this.repo.save(application);
        return  ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        this.repo.findById(applicationId).orElseThrow(
                () -> new ResourceNotFoundException("There is no application with id: " + applicationId)
        );

        this.repo.deleteById(applicationId);
    }

}


