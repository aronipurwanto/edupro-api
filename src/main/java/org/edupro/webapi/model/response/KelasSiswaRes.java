package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasSiswaRes {
    private String id;
    private String sesiAkademikId;
    private String kelasId;
    private String siswaId;
    private double nilaiTugas;
    private double nilaiUTS;
    private double nilaiUAS;
    private double nilaiAkhir;
    private String catatan;
}