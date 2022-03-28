package com.ninjaone.rmm.domain.exceptions;

import com.ninjaone.rmm.domain.common.ViolationEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Control exceptions in authorizer app
 */
@Getter
public class AuthorizerException extends RuntimeException{
    private final List<ViolationEnum> violationTypeList;
    public AuthorizerException(String message, List<ViolationEnum> violationTypeList){
        super(message);
        this.violationTypeList = violationTypeList;
    }

    public AuthorizerException(String message, ViolationEnum violationType){
        super(message);
        this.violationTypeList = new ArrayList<>();
        violationTypeList.add(violationType);
    }
}
