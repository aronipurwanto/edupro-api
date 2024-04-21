package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_ROLE")
public class RoleEntity {
    @Id
    private Integer id;

    @Column(name = "ROLE_NAME", length = 64)
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }
}
