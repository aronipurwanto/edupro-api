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
@Table(name = "T_SISWA")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="SWID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="SWCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="SWCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="SWUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="SWUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="SWSTAT"))
})
public class SiswaEntity extends BaseIdEntity {
	@Column(name = "SWNM", length = 100, nullable = false)
	private String nama;

	@Column(name = "SWNISN", length = 20)
	private String nisn;
	
	@Column(name = "SWKOTALAHIR", length = 50)
	private String kotaTempatLahir;
	
	@Column(name = "SWTGLLAHIR")
	private LocalDate tanggalLahir;

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
