package com.onboarding.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class EmployeeDetailsRequest {

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String jobTitle;
    private String department;
    private String startDate;
    private String nationalId;
    private String socialSecurityNumber;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactPhoneNumber;
    private List<DocumentMetadata> documentMetadata;
    private List<MultipartFile> documents;
}
