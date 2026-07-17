package com.tertal.konpeito.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    public enum Status {
        SAVED,
        APPLIED,
        INTERVIEW,
        OFFERED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    private String position;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer salary;

    private String jobUrl;

    private LocalDate date;

}