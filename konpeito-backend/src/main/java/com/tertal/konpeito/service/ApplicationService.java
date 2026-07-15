package com.tertal.konpeito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tertal.konpeito.dto.ApplicationFilterDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.repository.ApplicationRepository;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository repo;

    public List<Application> getApplications(ApplicationFilterDto filter) {
        return this.repo.findApplications(filter.getPosition(), filter.getStatus());
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

}
