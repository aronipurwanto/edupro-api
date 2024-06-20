package org.edupro.webapi.academic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicSessionRes {
    private String id;
    private String academicYearId;
    private String academicYearName;
    private Integer semester;
    private LocalDate startDate;
    private LocalDate endDate;
    private DataStatus status;
}
