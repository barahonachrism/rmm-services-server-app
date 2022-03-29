package com.ninjaone.rmm.domain.vo;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@QueryEntity
@Getter
@Setter
@NoArgsConstructor
public class DeviceVo {
   private UUID id;
   private UUID deviceTypeId;
   private String systemName;
   private String deviceType;
   private boolean hasAntivirus;
}
