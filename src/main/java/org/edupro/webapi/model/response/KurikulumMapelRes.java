package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KurikulumMapelRes {
    private Integer idLembaga;
    private String kode;
    private String nama;
    private DataStatus status;
}
