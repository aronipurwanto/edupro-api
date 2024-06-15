package org.edupro.webapi.student.model;

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
public class StudentRes {
    private String id;
    private String nisn;
    private String nama;
    private String kotaTempatLahir;
    private LocalDate tanggalLahir;
    private String gender;
    private String agama;
    private String golDarah;
    private String noTelp;
    private String email;
    private DataStatus status;
}
