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

import com.tertal.konpeito.dto.ApplicationDto;
import com.tertal.konpeito.dto.ApplicationFilterDto;
import com.tertal.konpeito.service.ApplicationService;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService service;

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getApplications(
            @ModelAttribute ApplicationFilterDto filter
    ) {
        List<ApplicationDto> applications = this.service.getApplications(filter);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> getApplication(
            @PathVariable("id") Long applicationId
    ) {
        ApplicationDto application = this.service.getApplication(applicationId);
        return ResponseEntity.ok(application);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> addApplication(
            @RequestBody ApplicationDto application
    ) {
        ApplicationDto savedApplication = this.service.addApplication(application);
        return new ResponseEntity<>(savedApplication, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDto> updateApplication(
            @PathVariable("id") Long applicationId,
            @RequestBody ApplicationDto application
    ) {
        ApplicationDto savedApplication = this.service.updateApplication(
                applicationId, application);
        return ResponseEntity.ok(savedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable("id") Long applicationId
    ) {
        this.service.deleteApplication(applicationId);
        return ResponseEntity.ok("Application deleted");
    }

}