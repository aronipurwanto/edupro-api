package org.edupro.webapi.model.request;

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
public class LevelReq {
    private String id;
    @NotEmpty
    @Size(min = 32, max = 36, message = "Nama minimal 4 dan maximal 50")
    private String idLembaga;

    @NotEmpty
    @Size(min = 4, max = 20, message = "Nama minimal 4 dan maximal 50")
    private String kode;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Nama minimal 4 dan maximal 50")
    private String nama;
    private Integer noUrut;
}
