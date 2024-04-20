/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import org.edupro.webapi.constant.DataStatus;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_NILAI_SISWA")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="NSCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="NSCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="NSUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="NSUPUID"))
})
public class NilaiSiswaEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6633650732514656422L;

	@Id
	@Column(name = "NSID")
	private String id;

	@Column(name = "SAID", nullable = false, length = 36)
	private String sesiAkademikId;

	@ManyToOne
	@JoinColumn(name = "SAID", insertable = false, updatable = false)
	private SesiAkademikEntity sesiAkademik;
	
	@Column(name = "TAID", nullable = false, length = 36)
	private String tahunAjaranId;

	@ManyToOne
	@JoinColumn(name = "TAID", insertable = false, updatable = false)
	private TahunAjaranEntity tahunAjaran;

	@Column(name = "SAURUT", nullable = false)
	private Integer urut; // 1 = ganjil, 2 = genap

	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kodeLevel;

	@Column(name = "MAPELKD", length=10, nullable = false)
	private String kodeMapel;

	@Column(name = "PERSONID", nullable = false)
	private String pengampuId;

	@Column(name = "SWID", nullable = false, length = 36)
	private String siswaId;

	@ManyToOne
	@JoinColumn(name = "SWID", insertable = false, updatable = false)
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

	@Default
	@Column(name = "KURSTAT", length = 20)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
