package com.saintrivers.mediaspringboot.model.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName) {
        super("no " + resourceName + " found with provided id");
    }

    public ResourceNotFoundException(String resourceName, String id) {
        super("no " + resourceName + " found with id: " + id);
    }

    public ResourceNotFoundException() {
        super("no resource found with provided id");
    }
}
