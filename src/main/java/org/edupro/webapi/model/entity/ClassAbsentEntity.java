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
@Table(name = "t_class_absent")
public class ClassAbsentEntity extends BaseIdEntity {
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
