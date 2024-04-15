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
public class RuanganReq {
    @NotEmpty
    @Size(min = 4, max = 20, message = "Kode minimal 4 dan maximal 20")
    private String kode;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Kode minimal 4 dan maximal 50")
    private String nama;

    @NotNull(message = "kapasitas tidak boleh")
    private Integer kapasitas;

    @NotEmpty
    @Size(min = 4, max = 20, message = "Kode minimal 4 dan maximal 20")
    private String kodeGedung;
}
