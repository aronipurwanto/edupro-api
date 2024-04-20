package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.model.entity.SesiAkademikEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiSiswaRes {
    private String id;
    private String sesiAkademikId;
    private SesiAkademikEntity sesiAkademik;
    private String tahunAjaranId;
    private Integer urut; // 1 = ganjil, 2 = genap
    private String mapelId;
    private String kodeLevel;
    private String kodeMapel;
    private String pengampuId;
    private String siswaId;
    private double nilaiTugas;
    private double nilaiUTS;
    private double nilaiUAS;
    private double nilaiAkhir;
    private String catatan;
}
