package com.onboarding.DTO;

import com.onboarding.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsResponse {

    private long empId;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String jobTitle;
    private String department;
    private String employeeId;
    private String startDate;
    private String nationalId;
    private String socialSecurityNumber;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactPhoneNumber;
    private Status status;
    private List<DocumentResponse> documents;
}
