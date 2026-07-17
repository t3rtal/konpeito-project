package com.tertal.konpeito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {

    private long totalApplication;
    private long offers;
    private long interviews;
    private long rejections;

}
