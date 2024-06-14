/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_person")
public class PersonEntity extends BaseIdEntity {
	@Column(name = "user_id", length = 100)
	private String userId;

	@Column(name = "nik", length = 50)
	private String nik; // NIK KTP

	@Column(name = "person_no", length = 50, nullable = false)
	private String nomor;
	
	@Column(name = "name", length = 100, nullable = false)
	private String nama;
	
	@Column(name = "address", length = 255)
	private String alamatTinggal;
	
	@Column(name = "dob")
	private LocalDate tanggalLahir;

	@Column(name = "pob", length = 100)
	private String tempatLahir;

	@Column(name = "gender", length = 20)
	private String gender;

	@Column(name = "religion", length = 20)
	private String agama;

	@Column(name = "blood_type", length = 2)
	private String golDarah;

	@Column(name = "no_telp", length = 20)
	private String noTelp;

	@Column(name = "email", length = 100)
	private String email;

	public PersonEntity(String nomor, String nama, String nik) {
		this.nomor = nomor;
		this.nama = nama;
		this.nik = nik;
	}
}
