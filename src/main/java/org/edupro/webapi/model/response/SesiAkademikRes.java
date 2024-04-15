package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesiAkademikRes {
    private Integer tahunPelajaran;
    private Integer urut;
    private String kodeKurikulum;
    private DataStatus status;
}
