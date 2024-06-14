package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_lookup")
public class LookupEntity extends BaseIdEntity{
    @Column(name = "groups", length = 32, nullable = false)
    private String group;

    @Column(name = "code", length = 32, nullable = false)
    private String kode;

    @Column(name = "name", length = 128, nullable = false)
    private String nama;

    @Column(name = "position", nullable = false)
    private Integer position;

    public LookupEntity(String id, String group, String kode, String nama, Integer position) {
        this.setId(id);
        this.group = group;
        this.kode = kode;
        this.nama = nama;
        this.position = position;
    }
}
