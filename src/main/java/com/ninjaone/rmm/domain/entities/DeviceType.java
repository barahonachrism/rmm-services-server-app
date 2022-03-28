package com.ninjaone.rmm.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "device_type")
public class DeviceType {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;
    private String name;
    private Double antivirusCost;
    private Double deviceManagementCost;
}