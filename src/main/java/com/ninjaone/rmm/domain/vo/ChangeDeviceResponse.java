package com.ninjaone.rmm.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeDeviceResponse extends  BaseResponse{
    private DeviceVo device;
}
