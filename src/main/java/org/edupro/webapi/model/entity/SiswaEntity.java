/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
		@AttributeOverride(name = "createdAt", column = @Column(name="SWCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="SWCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="SWUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="SWUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="SWSTAT"))
})
public class SiswaEntity extends BaseEntity {

	@Id
	@Column(name = "SWID", length = 36, nullable = false)
	private String id;
	
	@Column(name = "SWNM", length = 100, nullable = false)
	private String nama;

	@Column(name = "SWNISN", length = 20)
	private String nisn;
	
	@Column(name = "SWKOTALAHIR", length = 50)
	private String kotaTempatLahir;
	
	@Column(name = "SWTGLLAHIR")
	private LocalDate tanggalLahir;
}
