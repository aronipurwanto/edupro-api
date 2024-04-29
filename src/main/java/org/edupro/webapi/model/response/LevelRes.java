package org.edupro.webapi.model.response;

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
public class LevelRes {
    private String id;
    private String idLembaga;
    private String kode;
    private String nama;
    private Integer noUrut;
}
