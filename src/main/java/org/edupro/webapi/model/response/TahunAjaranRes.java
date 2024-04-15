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
public class TahunAjaranRes {
    private String id;
    private String nama;
    private String kodeKurikulum;
    private DataStatus status;
}
