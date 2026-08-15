package com.tertal.konpeito.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.dto.ApplicationFilterDto;

public interface ApplicationService {


    List<ApplicationDto> getApplications(ApplicationFilterDto filter, Pageable pageable);

    ApplicationDto getApplication(Long applicationId);

    ApplicationDto addApplication(ApplicationDto applicationDto);

    ApplicationDto updateApplication(
            Long applicationId, ApplicationDto applicationDto
    );

    void deleteApplication(Long applicationId);

}
