package org.edupro.webapi.building.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingRoomRes {
    private String id;
    private String kode;
    private String nama;
    private Integer kapasitas;
    private String gedungId;
    private String kodeGedung;
    private String namaGedung;
    private DataStatus status;
}
