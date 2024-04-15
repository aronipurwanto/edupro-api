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
public class LookupRes {
    private Integer id;
    private String group;
    private String kode;
    private String nama;
    private Integer urutan;
    private DataStatus status;
}
