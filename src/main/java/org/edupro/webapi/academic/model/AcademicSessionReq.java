package org.edupro.webapi.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicSessionReq {
    private String id;

    @NotNull(message = "Tahun Ajaran Id wajid diisi")
    @Size(min = 32, max = 36, message = "Tahun Ajaran Id minimal 36 dan maksimal 36")
    private String tahunAjaranId;

    @NotNull(message = "Semester wajid diisi")
    private Integer semester;

    @NotNull(message = "Kurikulum Id wajid diisi")
    @Size(min = 32, max = 36, message = "Kurikulum Id minimal 36 dan maksimal 36")
    private String kurikulumId;

    @NotEmpty(message = "Kode Kurikulum wajid diisi")
    @Size(min = 2, max = 20, message = "Kode kurikulum minimal 2 dan maksimal 20")
    private String kodeKurikulum;
}