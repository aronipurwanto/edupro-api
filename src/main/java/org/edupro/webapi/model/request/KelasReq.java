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
public class KelasReq {
    @NotNull(message = "Id Lembaga tidak boleh kosong")
    private Integer idLembaga;

    @NotNull(message = "Tahun Ajaran tidak boleh kosong")
    private Integer tahunAjaranId;

    @NotEmpty(message = "Kode tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Kode minimal 2 dan maximal 20")
    private String kode;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Nama minimal 2 dan maximal 20")
    private String nama;

    @NotEmpty(message = "Kode Ruangan tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Kode Ruangan minimal 2 dan maximal 20")
    private String kodeRuangan;
}
