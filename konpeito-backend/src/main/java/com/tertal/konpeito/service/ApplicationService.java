package com.tertal.konpeito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tertal.konpeito.model.Application;
import com.tertal.konpeito.repository.ApplicationRepo;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepo repo;

    public List<Application> getAllApplications() {
        return this.repo.findAll();
    }

    public Application getApplication(int id) {
        return this.repo.findById(id).orElse(new Application());
    }

    public void addApplication(Application application) {
        this.repo.save(application);
    }

    public void updateApplication(Application application) {
        this.repo.save(application);
    }

    public void deleteApplication(int id) {
        this.repo.deleteById(id);
    }

    public List<Application> getApplicationsByStatus(Application.Status status) {
        return this.repo.findByStatus(status);
    }

    public List<Application> getApplicationsByPosition(String position) {
        return this.repo.findByPosition(position);
    }

    public List<Application> getApplicationsByStatusAndPosition(Application.Status status, String position) {
        return this.repo.findByStatusAndPosition(status, position);
    }

}
