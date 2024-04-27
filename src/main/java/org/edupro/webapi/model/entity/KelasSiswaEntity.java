package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.constant.DataStatus;

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
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="KLSSISWAID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="KLSSISWACRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KLSSISWACRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KLSSISWAUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KLSSISWAUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="KLSSISWASTAT"))
})
public class KelasSiswaEntity extends BaseIdEntity {
	@Column(name = "SAID", nullable = false, length = 36)
	private String sesiAkademikId;

	@ManyToOne
	@JoinColumn(name = "SAID", insertable = false, updatable = false)
	private SesiAkademikEntity sesiAkademik;

	@Column(name = "KLSID", nullable = false, length = 36)
	private String kelasId;

	@ManyToOne
	@JoinColumn(name = "KLSID", insertable = false, updatable = false)
	private KelasEntity kelas;

	@Column(name = "SISWAID", nullable = false, length = 36)
	private String siswaId;

	@ManyToOne
	@JoinColumn(name = "SISWAID", insertable = false, updatable = false)
	private SiswaEntity siswa;

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

	@Builder.Default
	@Column(name = "KURSTAT", length = 20)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
