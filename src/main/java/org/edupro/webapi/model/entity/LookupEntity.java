package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_LOOKUP")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name="LKID")),
        @AttributeOverride(name = "createdAt", column = @Column(name="LKCRD")),
        @AttributeOverride(name = "createdBy", column = @Column(name="LKCRUID")),
        @AttributeOverride(name = "updatedAt", column = @Column(name="LKUPD")),
        @AttributeOverride(name = "updatedBy", column = @Column(name="LKUID")),
        @AttributeOverride(name = "status", column = @Column(name="LKSTAT"))
})
public class LookupEntity extends BaseIdEntity{
    @Column(name = "LKGRP", length = 32, nullable = false)
    private String group;

    @Column(name = "LKKD", length = 32, nullable = false)
    private String kode;

    @Column(name = "LKNM", length = 128, nullable = false)
    private String nama;

    @Column(name = "LKURT", nullable = false)
    private Integer urutan;

    @Builder.Default
    @Column(name = "KURSTAT", length = 20)
    @Enumerated(EnumType.STRING)
    private DataStatus status = DataStatus.AKTIF;

    public LookupEntity(String id, String group, String kode, String nama, Integer urutan) {
        this.setId(id);
        this.group = group;
        this.kode = kode;
        this.nama = nama;
        this.urutan = urutan;
    }
}
