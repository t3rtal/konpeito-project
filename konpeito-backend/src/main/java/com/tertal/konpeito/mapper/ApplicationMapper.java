package com.tertal.konpeito.mapper;

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.entity.User;

public class ApplicationMapper {

    public static ApplicationDto mapToApplicationDto(Application application) {
        return new ApplicationDto(
                application.getId(),
                application.getCompany(),
                application.getPosition(),
                application.getStatus(),
                application.getSalary(),
                application.getJobUrl(),
                application.getDate()
        );
    }

    public static Application mapToApplication(ApplicationDto applicationDto, User user) {
        return Application.builder()
                .id(applicationDto.getId())
                .user(user)
                .company(applicationDto.getCompany())
                .position(applicationDto.getPosition())
                .status(applicationDto.getStatus())
                .salary(applicationDto.getSalary())
                .jobUrl(applicationDto.getJobUrl())
                .date(applicationDto.getDate())
                .build();
    }

}
