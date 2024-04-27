package org.edupro.webapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasReq {
    private String id;
    private String kode;
    private String nama;
    private String ruangId;
    private String kodeRuangan;
    private String lembagaId;
    private String tahunAjaranId;
    private String levelId;
    private String SesiAkademikId;
    private String waliKelasId;
}
