package org.edupro.webapi.model.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonReq {
    private String id;
    private String userId;
    private String nomor;
    private String nama;
    private String alamatTinggal;
    private String nik; // NIK KTP
    @Temporal(TemporalType.DATE)
    private LocalDate tanggalLahir;
    private String tempatLahir;
    private String gender;
    private String agama;
    private String golDarah;
    private String noTelp;
    private String email;
}
