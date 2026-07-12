package com.tertal.konpeito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tertal.konpeito.model.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {
}
