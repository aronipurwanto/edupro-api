package org.edupro.webapi.academic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYearRes {
    private String id;
    private String name;
    private String curriculumId;
    private String curriculumCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private DataStatus status;
}
