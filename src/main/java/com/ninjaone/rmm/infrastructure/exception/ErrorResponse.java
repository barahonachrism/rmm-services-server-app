package com.ninjaone.rmm.infrastructure.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ErrorResponse {
    private String error;
    public ErrorResponse(String error){
        this.error = error;
    }
}
