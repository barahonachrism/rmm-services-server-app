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

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "service_catalog_id")
    private ServiceCatalog serviceCatalog;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}