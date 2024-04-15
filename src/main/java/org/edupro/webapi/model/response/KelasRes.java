package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasRes {
    private Integer idLembaga;
    private Integer tahunAjaranId;
    private String kode;
    private String nama;
    private String kodeRuangan;
    private String namaRuangan;
}
