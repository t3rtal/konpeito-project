package com.tertal.konpeito.dto;

import com.tertal.konpeito.entity.Application;

import lombok.Data;

@Data
public class ApplicationFilterDto {

    private String position;
    private Application.Status status;

}
