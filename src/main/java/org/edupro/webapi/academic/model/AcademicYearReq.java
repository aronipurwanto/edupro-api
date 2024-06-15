package org.edupro.webapi.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearReq {
    private String id;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Nama tidak boleh kosong")
    private String nama;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Size(min = 32, max = 36, message = "Nama tidak boleh kosong")
    private String kurikulumId;

    @NotEmpty(message = "Kode Kurikulum tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Nama tidak boleh kosong")
    private String kodeKurikulum;
}
