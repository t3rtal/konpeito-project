package com.tertal.konpeito.mapper;

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.entity.Application;

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

    public static Application mapToApplication(ApplicationDto applicationDto) {
        return new Application(
                applicationDto.getId(),
                applicationDto.getCompany(),
                applicationDto.getPosition(),
                applicationDto.getStatus(),
                applicationDto.getSalary(),
                applicationDto.getJobUrl(),
                applicationDto.getDate()
        );
    }

}
