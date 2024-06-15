package org.edupro.webapi.person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRes {
    private String id;
    private String userId;
    private String nomor;
    private String nama;
    private String alamatTinggal;
    private String nik; // NIK KTP
    private LocalDate tanggalLahir;
    private String tempatLahir;
    private String gender;
    private String agama;
    private String golDarah;
    private String noTelp;
    private String email;
    private DataStatus status;
}
