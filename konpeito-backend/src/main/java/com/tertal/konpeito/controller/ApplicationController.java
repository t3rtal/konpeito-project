package com.tertal.konpeito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tertal.konpeito.dto.ApplicationFilterDto;
import com.tertal.konpeito.entity.Application;
import com.tertal.konpeito.service.ApplicationService;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService service;

    @GetMapping
    public ResponseEntity<List<Application>> getApplications(
            @ModelAttribute ApplicationFilterDto filter
    ) {
        List<Application> applications = this.service.getApplications(filter);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplication(
            @PathVariable("id") int applicationId
    ) {
        Application application = this.service.getApplication(applicationId);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addApplication(
            @RequestBody Application application
    ) {
        this.service.addApplication(application);
        return new ResponseEntity<>("Application added", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateApplication(
            @RequestBody Application application
    ) {
        this.service.updateApplication(application);
        return new ResponseEntity<>("Application updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable("id") int applicationId
    ) {
        this.service.deleteApplication(applicationId);
        return new ResponseEntity<>("Application deleted", HttpStatus.OK);
    }

}
