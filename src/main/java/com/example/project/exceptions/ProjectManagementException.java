package com.example.project.exceptions;

public class ProjectManagementException extends RuntimeException {

    public ProjectManagementException(String message, Throwable cause ) {
        super(message, cause);
    }

    public ProjectManagementException(String message ) {
        super(message);
    }

    public ProjectManagementException(Throwable cause ) {
        super(cause);
    }

}
