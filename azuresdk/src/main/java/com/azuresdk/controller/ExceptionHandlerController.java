package com.azuresdk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.azure.storage.blob.models.BlobStorageException;
import com.azuresdk.dtos.ErrorDTO;
import com.azuresdk.exception.NameAlreadyExistsException;
import com.azuresdk.exception.NameDoesNotExistException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BlobStorageException.class)
    public ResponseEntity<String> handleContainerNotFound(BlobStorageException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NameDoesNotExistException.class)
    public String handleNameDoesNotExistException(NameDoesNotExistException ex) {
         return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NameAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleNameAlreadyExistsException() {
		return ResponseEntity.internalServerError().body(new ErrorDTO("name", "Name already exists"));
	}
    
}
