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
public class CurriculumSubjectRes {
    private Integer idLembaga;
    private String kode;
    private String nama;
    private DataStatus status;
}
