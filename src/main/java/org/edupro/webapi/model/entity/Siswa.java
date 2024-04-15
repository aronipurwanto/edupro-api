/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
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
public class Siswa extends BaseEntity {

	@Id
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
