package com.tertal.konpeito.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tertal.konpeito.config.TenantContext;
import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.dto.ApplicationFilterDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.entity.User;
import com.tertal.konpeito.exception.ResourceNotFoundException;
import com.tertal.konpeito.mapper.ApplicationMapper;
import com.tertal.konpeito.repository.ApplicationRepository;
import com.tertal.konpeito.repository.UserRepository;
import com.tertal.konpeito.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<ApplicationDto> getApplications(
            ApplicationFilterDto filter, Pageable pageable
    ) {
        List<Application> applications = this.applicationRepository.findApplications(
                filter.getPosition(), filter.getStatus(), pageable).getContent();

        return applications.stream()
                .map(ApplicationMapper::mapToApplicationDto)
                .toList();
    }

    @Override
    public ApplicationDto getApplication(Long applicationId) {
        Long userId = TenantContext.getCurrentTenant();
        Application application = this.applicationRepository
                .findByIdAndUserId(applicationId, userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "There is no application with id: " + applicationId)
                );
        return ApplicationMapper.mapToApplicationDto(application);
    }

    @Override
    public ApplicationDto addApplication(ApplicationDto applicationDto) {
        Long user_id = TenantContext.getCurrentTenant();
        Optional<User> user = this.userRepository.findById(user_id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Invalid user_id");
        }

        Application application = ApplicationMapper.mapToApplication(
                applicationDto, user.get());
        Application savedApplication = this.applicationRepository.save(application);
        return ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    public ApplicationDto updateApplication(
            Long applicationId, ApplicationDto applicationDto
    ) {
        Long userId = TenantContext.getCurrentTenant();
        Application application = this.applicationRepository
                .findByIdAndUserId(applicationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no application with id: " + applicationId));

        application.setCompany(applicationDto.getCompany());
        application.setPosition(applicationDto.getPosition());
        application.setStatus(applicationDto.getStatus());
        application.setSalary(applicationDto.getSalary());
        application.setJobUrl(applicationDto.getJobUrl());
        application.setDate(applicationDto.getDate());

        Application savedApplication = this.applicationRepository.save(application);
        return  ApplicationMapper.mapToApplicationDto(savedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        Long userId = TenantContext.getCurrentTenant();
        Application application = this.applicationRepository.findByIdAndUserId(applicationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no application with id: " + applicationId));

        this.applicationRepository.delete(application);
    }

}