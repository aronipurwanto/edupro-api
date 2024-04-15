package org.edupro.webapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KurikulumMapelReq {
    @NotNull(message = "Lembaga Id tidak boleh kosong")
    private Integer idLembaga;

    @NotEmpty(message = "Kode kurikulum tidak boleh kosong")
    @Size(min = 4, max = 20, message = "Kode kurikulum minimal 4 dan maksimal 20")
    private String kodeKurikulum;

    @NotEmpty(message = "kode tidak boleh kosong")
    @Size(min = 4, max = 20, message = "Kode minimal 4 dan maksimal 20")
    private String kode;

    @NotEmpty(message = "tidak boleh kosong")
    @Size(min = 2, max = 10, message = "Kode Kelompok Mapel min 2 dan maksimal 10")
    private String kodeKelompokMapel;
}
