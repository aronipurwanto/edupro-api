package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasRes {
    private String id;
    private String kode;
    private String nama;
    private String ruangId;
    private String kodeRuangan;
    private String lembagaId;
    private String tahunAjaranId;
    private String levelId;
    private String sesiAkademikId;
    private String waliKelasId;
    private List<KelasSiswaRes> siswaList;
}
