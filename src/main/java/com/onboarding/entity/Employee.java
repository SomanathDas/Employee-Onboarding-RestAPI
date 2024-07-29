package com.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onboarding.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, VERIFIED

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Document> documents;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
