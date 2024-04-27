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
@Table(name = "T_PERSONEL")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name="PERSONID")),
	@AttributeOverride(name = "createdAt", column = @Column(name="PERSONCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="PERSONCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="PERSONUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="PERSONUPUID"))
})
public class PersonEntity extends BaseIdEntity {
	@Column(name = "PERSONUID", length = 100)
	private String userId;

	@Column(name = "PERSONNO", length = 50, nullable = false)
	private String nomor;
	
	@Column(name = "PERSONNM", length = 100, nullable = false)
	private String nama;
	
	@Column(name = "PERSONADDR", length = 255)
	private String alamatTinggal;
	
	@Column(name = "PERSONNIK", length = 50)
	private String nik; // NIK KTP
	
	@Column(name = "PERSONDOB")
	private LocalDate tanggalLahir;

	@Column(name = "PERSONPOB", length = 100)
	private String tempatLahir;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "AGAMA", length = 20)
	private String agama;

	@Column(name = "GOLDARAH", length = 2)
	private String golDarah;

	@Column(name = "NOTELP", length = 20)
	private String noTelp;

	@Column(name = "EMAIL", length = 100)
	private String email;
}
