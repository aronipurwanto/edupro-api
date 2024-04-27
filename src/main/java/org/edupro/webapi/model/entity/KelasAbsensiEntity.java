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
@Table(name = "T_KELAS_ABSENSI")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="KLSABSENID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="KLSABSENCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KLSABSENCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KLSABSENUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KLSABSENUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="KLSABSENSTAT"))
})
public class KelasAbsensiEntity extends BaseIdEntity {
	@Column(name = "KLSSISWAID", length = 36, nullable = false)
	private String kelasSiswaId;

	@ManyToOne
	@JoinColumn(name = "KLSSISWAID", insertable = false, updatable = false)
	private KelasSiswaEntity kelasSiswa;

	@Column(name = "TGLABSENSI", nullable = false)
	private LocalDate tglAbsensi;

	@Column(name = "STATUSABSEN", length = 20, nullable = false)
	private String statusAbsen;
}
