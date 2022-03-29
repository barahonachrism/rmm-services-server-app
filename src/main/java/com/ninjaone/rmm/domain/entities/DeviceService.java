package com.ninjaone.rmm.domain.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device_service", uniqueConstraints = {
        @UniqueConstraint(name = "uc_deviceservice_device_id", columnNames = {"device_id", "service_catalog_id", "customer_id"})
})
public class DeviceService {
    @Id
    @Column(name = "id", nullable = false,columnDefinition = "uuid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "device_id",nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID deviceId;

    @Column(name = "service_catalog_id", nullable = true)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID serviceCatalogId;

    @Column(name = "customer_id", nullable = false)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", insertable = false, updatable = false)
    private Device device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_catalog_id",insertable = false,updatable = false)
    private ServiceCatalog serviceCatalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",insertable = false,updatable = false)
    private Customer customer;

}