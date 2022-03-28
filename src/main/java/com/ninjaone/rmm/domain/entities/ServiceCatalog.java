package com.ninjaone.rmm.domain.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service_catalog")
public class ServiceCatalog {
    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private String name;

    private Double cost;
}