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
public class CommonLembagaReq {
    @NotNull(message = "Lembaga Id tidak boleh kosong")
    private Integer idLembaga;

    @NotEmpty(message = "kode tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Kode minimal 4 dan maksimal 20")
    private String kode;

    @NotEmpty(message = "tidak boleh kosong")
    @Size(min = 2, max = 100, message = "Nama min 2 dan maksimal 10")
    private String nama;
}
