package org.edupro.webapi.curriculum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumRes {
    private String id;
    private String kode;
    private String nama;
    private Integer noUrut;
    private DataStatus status;
}
