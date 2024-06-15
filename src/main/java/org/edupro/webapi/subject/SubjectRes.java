package org.edupro.webapi.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRes {
    private String id;
    private String kode;
    private String nama;
    private DataStatus status;
}