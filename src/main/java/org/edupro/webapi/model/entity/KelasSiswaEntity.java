package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "T_KELAS_SISWA")
public class KelasSiswaEntity extends BaseIdEntity {
	@Column(name = "SAID", nullable = false, length = 36)
	private String sesiAkademikId;

	@ManyToOne
	@JoinColumn(name = "SAID", insertable = false, updatable = false)
	private AcademicSessionEntity sesiAkademik;

	@Column(name = "KLSID", nullable = false, length = 36)
	private String kelasId;

	@ManyToOne
	@JoinColumn(name = "KLSID", insertable = false, updatable = false)
	private ClassEntity kelas;

	@Column(name = "SISWAID", nullable = false, length = 36)
	private String siswaId;

	@ManyToOne
	@JoinColumn(name = "SISWAID", insertable = false, updatable = false)
	private StudentEntity siswa;

	@Column(name = "NILAI_TUGAS")
	private double nilaiTugas;

	@Column(name = "NILAI_UTS")
	private double nilaiUTS;

	@Column(name = "NILAI_UAS")
	private double nilaiUAS;

	@Column(name = "NILAI_AKHIR")
	private double nilaiAkhir;

	@Column(name = "CATATAN")
	private String catatan;
}
