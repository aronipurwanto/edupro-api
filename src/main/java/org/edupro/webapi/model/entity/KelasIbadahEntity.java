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
@Table(name = "T_KELAS_IBADAH")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="KLSIBDID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="KLSIBDCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KLSIBDCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KLSIBDUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KLSIBDUPUID"))
})
public class KelasIbadahEntity extends BaseIdEntity {
	@Column(name = "KLSSISWAID", nullable = false, length = 36)
	private String kelasSiswaId;

	@Column(name = "IBDID", nullable = false, length = 36)
	private String ibadahId;

	@ManyToOne
	@JoinColumn(name = "IBDID", insertable = false, updatable = false)
	private LookupEntity lookup;

	@Column(name = "IBDDate")
	private LocalDate tglIbadah;

	@Column(name = "IBDSTS", nullable = false, length = 20)
	private String ibadahStatus;

	@Column(name = "CATATAN", length = 100)
	private String catatan;

	@Column(name = "ISDONE")
	private Boolean isDone;
}
