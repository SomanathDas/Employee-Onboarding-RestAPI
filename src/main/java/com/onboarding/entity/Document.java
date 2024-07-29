package com.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentId;

    private String documentName;
    private String documentType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] documentData;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonBackReference
    private Employee employee;
}
