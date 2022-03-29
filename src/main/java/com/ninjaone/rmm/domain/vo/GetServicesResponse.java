package com.ninjaone.rmm.domain.vo;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetServicesResponse extends BaseResponse{
    List<ServiceVo> services;
}
