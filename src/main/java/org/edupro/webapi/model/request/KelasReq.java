package org.edupro.webapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(min = 32, max = 36, message = "ruangId minimal 32 dan maximal 36")
    private String ruangId;

    private String kodeRuangan;

    @NotEmpty
    @Size(min = 32, max = 36, message = "lembagaId minimal 32 dan maximal 36")
    private String lembagaId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "tahunAjaranId minimal 32 dan maximal 36")
    private String tahunAjaranId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "levelId minimal 32 dan maximal 36")
    private String levelId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "SesiAkademikId minimal 32 dan maximal 36")
    private String SesiAkademikId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "waliKelasId minimal 32 dan maximal 36")
    private String waliKelasId;
}
