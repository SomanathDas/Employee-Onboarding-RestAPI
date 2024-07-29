package com.onboarding.controller;

import com.onboarding.DTO.EmployeeDetailsResponse;
import com.onboarding.entity.Employee;
import com.onboarding.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
public class HRController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Demo from HR");
    }

    @GetMapping("/pending-requests")
    public ResponseEntity<List<Employee>> getPendingRequests() {
        try {
            List<Employee> pendingEmployees= employeeService.getPendingRequests();
            if (pendingEmployees.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(pendingEmployees);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve pending employee requests", e);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDetailsResponse> getEmployeeDetails(@PathVariable Long id) {
        EmployeeDetailsResponse employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/approve-request/{employeeId}")
    public ResponseEntity<String> approveRequest(@PathVariable Long employeeId) {
        employeeService.approveRequest(employeeId);
        return ResponseEntity.ok("Request approved successfully");
    }

    @DeleteMapping("/reject-request/{employeeId}")
    public ResponseEntity<String> rejectRequest(@PathVariable Long employeeId) {
        employeeService.rejectRequest(employeeId);
        return ResponseEntity.ok("Request rejected successfully");
    }
}
