package com.onboarding.service;

import com.onboarding.DTO.DocumentResponse;
import com.onboarding.DTO.EmployeeDetailsRequest;
import com.onboarding.DTO.EmployeeDetailsResponse;
import com.onboarding.entity.Document;
import com.onboarding.entity.Employee;
import com.onboarding.entity.User;
import com.onboarding.entity.enums.Status;
import com.onboarding.exceptio_handling.exceptions.ResourceNotFoundException;
import com.onboarding.repository.DocumentRepository;
import com.onboarding.repository.EmployeeRepository;
import com.onboarding.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentRepository documentRepository;


    public EmployeeDetailsResponse findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        EmployeeDetailsResponse response = new EmployeeDetailsResponse();
        response.setEmpId(employee.getEmpId());
        response.setName(employee.getName());
        response.setAddress(employee.getAddress());
        response.setEmail(employee.getEmail());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setJobTitle(employee.getJobTitle());
        response.setDepartment(employee.getDepartment());
        response.setEmployeeId(employee.getEmployeeId());
        response.setStartDate(employee.getStartDate());
        response.setNationalId(employee.getNationalId());
        response.setSocialSecurityNumber(employee.getSocialSecurityNumber());
        response.setBankAccountNumber(employee.getBankAccountNumber());
        response.setBankName(employee.getBankName());
        response.setIfscCode(employee.getIfscCode());
        response.setEmergencyContactName(employee.getEmergencyContactName());
        response.setEmergencyContactRelationship(employee.getEmergencyContactRelationship());
        response.setEmergencyContactPhoneNumber(employee.getEmergencyContactPhoneNumber());
        response.setStatus(employee.getStatus());

        List<DocumentResponse> documents = employee.getDocuments().stream()
                .map(doc -> {
                    DocumentResponse documentResponse = new DocumentResponse();
                    documentResponse.setDocumentId(doc.getDocumentId());
                    documentResponse.setDocumentName(doc.getDocumentName());
                    documentResponse.setDocumentType(doc.getDocumentType());
                    return documentResponse;
                }).collect(Collectors.toList());
        response.setDocuments(documents);
        return response;
    }


    public void submitDetails(String username, EmployeeDetailsRequest detailsRequest, List<MultipartFile> documents) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }

        Employee employee = new Employee();

        // Set employee details from the request
        employee.setName(detailsRequest.getName());
        employee.setAddress(detailsRequest.getAddress());
        employee.setEmail(detailsRequest.getEmail());
        employee.setPhoneNumber(detailsRequest.getPhoneNumber());
        employee.setDateOfBirth(detailsRequest.getDateOfBirth());
        employee.setJobTitle(detailsRequest.getJobTitle());
        employee.setDepartment(detailsRequest.getDepartment());
        employee.setStartDate(detailsRequest.getStartDate());
        employee.setNationalId(detailsRequest.getNationalId());
        employee.setSocialSecurityNumber(detailsRequest.getSocialSecurityNumber());
        employee.setBankAccountNumber(detailsRequest.getBankAccountNumber());
        employee.setBankName(detailsRequest.getBankName());
        employee.setIfscCode(detailsRequest.getIfscCode());
        employee.setEmergencyContactName(detailsRequest.getEmergencyContactName());
        employee.setEmergencyContactRelationship(detailsRequest.getEmergencyContactRelationship());
        employee.setEmergencyContactPhoneNumber(detailsRequest.getEmergencyContactPhoneNumber());
        employee.setStatus(Status.PENDING);

        List<Document> documentEntities = detailsRequest.getDocumentMetadata().stream()
                .map(metadata -> {
                    MultipartFile file = documents.stream()
                            .filter(doc -> doc.getOriginalFilename().equals(metadata.getDocumentName()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Document not found"+ metadata.getDocumentName()));
                    try {
                        Document document = new Document();
                        document.setDocumentName(metadata.getDocumentName());
                        document.setDocumentType(metadata.getDocumentType());
                        document.setMimeType(file.getContentType());
                        document.setDocumentData(file.getBytes());
                        document.setEmployee(employee);
                        return document;
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Failed to store document content", e);
                    }
                })
                .toList();
        employee.setDocuments(documentEntities);
        employee.setUser(user);
        employeeRepository.save(employee);
        //userRepository.save(user);
    }


    public List<Employee> getPendingRequests() {
        return employeeRepository.findByStatus(Status.PENDING);
    }

    public void approveRequest(Long employeeId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

            employee.setStatus(Status.VERIFIED);
            employeeRepository.save(employee);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Failed to approve employee request",e);
        }
    }

    @Transactional
    public void rejectRequest(Long employeeId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

            System.out.println("delete employee:-"+employee);

            documentRepository.deleteAll(employee.getDocuments());
            employeeRepository.delete(employee);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Failed to reject and delete employee request", e);
        }
    }
}
