package com.ninjaone.rmm.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
@NamedQuery(name = "device.deviceWithType",query = "select d from Device d inner join d.deviceType dt where d.id = :deviceId")
public class Device {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private String systemName;

    private boolean hasAntivirus;

    @Column(name = "device_type_id",nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID deviceTypeId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(name = "device_type_id", nullable = false, insertable = false, updatable = false)
    private DeviceType deviceType;

    @OneToMany(mappedBy = "device")
    private List<DeviceService> deviceServices;

}