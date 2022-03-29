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
@Table(name = "service_catalog", uniqueConstraints = {
        @UniqueConstraint(name = "uc_servicecatalog_name", columnNames = {"name"})
})
public class ServiceCatalog {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private String name;

    private Double cost;
}