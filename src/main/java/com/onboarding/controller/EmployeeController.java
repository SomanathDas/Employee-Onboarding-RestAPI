package com.onboarding.controller;

import com.onboarding.DTO.EmployeeDetailsRequest;
import com.onboarding.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/demo")
    public ResponseEntity<String> print(){
        return ResponseEntity.ok("Hello, this is an Employee!");
    }

    @PostMapping(value = "/submit-details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> submitDetails(
            @RequestPart("employeeDetails") EmployeeDetailsRequest detailsRequest,
            @RequestPart("documents") List<MultipartFile> documents
            ) {

        // Retrieve the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        employeeService.submitDetails(username, detailsRequest, documents);
        return ResponseEntity.ok("Details submitted successfully");
    }
}
