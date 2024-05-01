package org.edupro.webapi.model.response;

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
    private String namaLembaga;
    private String kode;
    private String nama;
    private Integer noUrut;
}
