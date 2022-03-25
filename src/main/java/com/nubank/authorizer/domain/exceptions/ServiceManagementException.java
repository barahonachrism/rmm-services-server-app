package com.nubank.authorizer.domain.exceptions;

public class ServiceManagementException extends RuntimeException{
    public ServiceManagementException(String message){
        super(message);
    }
    public ServiceManagementException(String message, Throwable e){
        super(message,e);
    }
}
