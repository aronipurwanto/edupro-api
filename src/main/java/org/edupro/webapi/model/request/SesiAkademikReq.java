package org.edupro.webapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesiAkademikReq {
    @NotNull(message = "Tahun pelajaran wajid diisi")
    private Integer tahunPelajaran;

    @NotNull(message = "Nomor Urut wajid diisi")
    private Integer urut;

    @NotEmpty(message = "Kode Kurikulum wajid diisi")
    @Size(min = 2, max = 20, message = "Kode kurikulum minimal 2 dan maksimal 20")
    private String kodeKurikulum;
}
