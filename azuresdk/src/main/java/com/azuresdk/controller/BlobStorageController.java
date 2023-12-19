package com.azuresdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azuresdk.service.BlobStorageService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/blobStorage")
@Validated
public class BlobStorageController {

    @Autowired
    private BlobStorageService blobStorageService;

    @Operation(summary = "POST a picture to the requested blobStorage. " +
        "If a file with the same name already exists, then that file is replaced")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void post(
            @RequestParam("container") @NotBlank String container,
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "", name = "fileName") String fileName) {
            blobStorageService.post(container, file, fileName);
    }

    @Operation(summary = "GET a picture to the requested blobStorage. " +
        "The name must exactly match the requested file")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> get(
        @PathVariable String fileName,
        @RequestParam String container) {
            byte[] data = blobStorageService.get(container,fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(data);
    }

    @Operation(summary = "DELETE a picture to the requested blobStorage. " +
        "The name must exactly match the requested file")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{fileName}")
    public void delete(
        @PathVariable String fileName,
        @RequestParam String container) {
            blobStorageService.delete(container, fileName);
    }


    
}
