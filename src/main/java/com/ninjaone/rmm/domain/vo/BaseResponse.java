package com.ninjaone.rmm.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * General response must has violations list, empty as default
 */
@Getter
@Setter
public class BaseResponse {
    private List<ErrorVo> errors = new ArrayList<>();
}
