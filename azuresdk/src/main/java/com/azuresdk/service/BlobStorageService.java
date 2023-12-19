package com.azuresdk.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Service
public class BlobStorageService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    public String post(String container, MultipartFile file, String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);
        BlobClient blobClient;
        if(fileName.isEmpty())
            blobClient = containerClient.getBlobClient(file.getOriginalFilename());
        else {
            blobClient = containerClient.getBlobClient(fileName);
        }
        try(InputStream input = file.getInputStream()) {
            blobClient.upload(input, file.getSize());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return blobClient.getBlobUrl();
    }

    public byte[] get(String container, String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.downloadStream(outputStream);
        return outputStream.toByteArray();
    }

    public void delete(String container, String fileName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.delete();
    }
    
}
