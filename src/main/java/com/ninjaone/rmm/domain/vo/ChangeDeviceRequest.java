package com.ninjaone.rmm.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChangeDeviceRequest extends BaseResponse {
    private DeviceVo device;
    private UUID customerId;
}
