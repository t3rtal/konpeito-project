package com.tertal.konpeito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tertal.konpeito.model.Application;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {

    List<Application> findByStatus(Application.Status status);
    List<Application> findByPosition(String position);
    List<Application> findByStatusAndPosition(Application.Status status, String position);

}
