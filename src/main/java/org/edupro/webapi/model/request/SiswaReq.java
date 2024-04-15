package org.edupro.webapi.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiswaReq {
    private String id;

    @NotEmpty
    @Size(min = 8, max = 20, message = "Kode minimal 8 dan maximal 20")
    private String nisn;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Kode minimal 4 dan maximal 100")
    private String nama;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Kode minimal 4 dan maximal 100")
    private String kotaTempatLahir;

    @NotNull
    private LocalDate tanggalLahir;
}
