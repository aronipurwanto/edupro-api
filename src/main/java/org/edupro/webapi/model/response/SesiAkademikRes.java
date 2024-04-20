package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesiAkademikRes {
    private String id;
    private String tahunAjaranId;
    private String tahunAjaranName;
    private String kurikulumId;
    private String kodeKurikulum;
    private String kurikulumName;
    private Integer urut;
    private DataStatus status;
}
