package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
