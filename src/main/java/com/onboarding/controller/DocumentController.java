package com.onboarding.controller;

import com.onboarding.entity.Document;
import com.onboarding.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'HR')")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        Document document = documentService.findDocumentById(id);

        String contentDisposition = String.format("inline; filename=\"%s\"", document.getDocumentName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + document.getDocumentName() + "\"")
                .contentType(MediaType.parseMediaType(document.getMimeType()))
                .body(document.getDocumentData());
    }
}
