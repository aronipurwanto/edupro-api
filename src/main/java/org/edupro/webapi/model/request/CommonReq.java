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
public class CommonReq {
    @NotEmpty
    @Size(min = 2, max = 10, message = "Kode minimal 2 dan maximal 10")
    private String kode;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Nama minimal 4 dan maximal 50")
    private String nama;
}
