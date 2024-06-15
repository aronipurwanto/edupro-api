package org.edupro.webapi.classes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRes {
    private String id;
    private String kode;
    private String nama;
    private String ruangId;
    private String kodeRuangan;
    private String lembagaId;
    private String namaLembaga;
    private String tahunAjaranId;
    private String namaTahunAjaran;
    private String levelId;
    private String namaLevel;
    private String sesiAkademikId;
    private Integer semester;
    private String waliKelasId;
    private String namaWaliKelas;
    private DataStatus status;
//    private List<KelasSiswaRes> siswaList;
}
