package com.ninjaone.rmm.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetDeviceResponse extends BaseResponse{
    private List<DeviceVo> devices;
}
