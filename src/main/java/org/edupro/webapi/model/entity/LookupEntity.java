package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_LOOKUP")
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name="LKCRD")),
        @AttributeOverride(name = "createdBy", column = @Column(name="LKCRUID")),
        @AttributeOverride(name = "updatedAt", column = @Column(name="LKUPD")),
        @AttributeOverride(name = "updatedBy", column = @Column(name="LKUID")),
        @AttributeOverride(name = "status", column = @Column(name="LKSTAT"))
})
public class LookupEntity extends BaseEntity{
    @Id
    @Column(name = "LKID", length = 36, nullable = false)
    private String id;

    @Column(name = "LKGRP", length = 32, nullable = false)
    private String group;

    @Column(name = "LKKD", length = 32, nullable = false)
    private String kode;

    @Column(name = "LKNM", length = 128, nullable = false)
    private String nama;

    @Column(name = "LKURT", nullable = false)
    private Integer urutan;
}
