package com.onboarding.service;

import com.onboarding.DTO.DocumentResponse;
import com.onboarding.entity.Document;
import com.onboarding.entity.Employee;
import com.onboarding.exceptio_handling.exceptions.ResourceNotFoundException;
import com.onboarding.repository.DocumentRepository;
import com.onboarding.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Document findDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id " + id));
    }

    public List<DocumentResponse> getDocumentsByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        
        return employee.getDocuments().stream()
                .map(document -> new DocumentResponse(
                        document.getDocumentId(),
                        document.getDocumentName(),
                        document.getDocumentType()
                )).collect(Collectors.toList());
    }
}
