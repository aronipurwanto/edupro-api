package org.edupro.webapi.model.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class SiswaRes {
    private String id;
    private String nisn;
    private String nama;
    private String kotaTempatLahir;
    private LocalDate tanggalLahir;
    private DataStatus status;
}
