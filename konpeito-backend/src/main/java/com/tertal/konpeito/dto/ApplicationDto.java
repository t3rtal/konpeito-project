package com.tertal.konpeito.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tertal.konpeito.entity.Application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private Long id;
    private String company;
    private String position;
    private Application.Status status;
    private Integer salary;
    private String jobUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

}
