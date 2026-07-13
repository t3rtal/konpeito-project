package com.tertal.konpeito.dto;

import com.tertal.konpeito.model.Application;

import lombok.Data;

@Data
public class ApplicationFilterDto {

    private String position;
    private Application.Status status;

}
