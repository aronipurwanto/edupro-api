package org.edupro.webapi.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.model.entity.SesiAkademikEntity;
import org.edupro.webapi.model.entity.SiswaEntity;
import org.edupro.webapi.model.entity.TahunAjaranEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NilaiSiswaReq {
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
