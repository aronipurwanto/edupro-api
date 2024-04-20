package org.edupro.webapi.model.entity;

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
@Table(name = "T_KELAS")
@AttributeOverrides({
		@AttributeOverride(name = "createdAt", column = @Column(name="KLSCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="KLSCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="KLSUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="KLSUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="KLSSTAT"))
})
public class KelasEntity extends BaseEntity {
	@Id
	@Column(name = "KLSID", nullable = false, length = 36)
	private String id;
	
	@Column(name = "KLSNM", length = 20, nullable = false)
	private String nama;

	@Column(name = "RUANGID", nullable = false, length = 36)
	private String ruangId;
	
	@Column(name = "RUANGKD", length = 20)
	private String kodeRuangan;

	@Column(name = "LBGID", nullable = false)
	private Integer idLembaga;

	@Column(name = "TAID", nullable = false)
	private String tahunAjaranId;

	@ManyToOne
	@JoinColumn(name = "TAID", insertable = false, updatable = false)
	private TahunAjaranEntity tahunAjaran;

	@Column(name = "KLSKD", length = 10, nullable = false)
	private String kode;

	@Column(name = "LVLID", nullable = false, length = 36)
	private String levelId;

	@ManyToOne
	@JoinColumn(name = "LVLID", insertable = false, updatable = false)
	private LevelEntity level;

	@Column(name = "SAID", nullable = false, length = 36)
	private String SesiAkademikId;

	@ManyToOne
	@JoinColumn(name = "SAID", insertable = false, updatable = false)
	private SesiAkademikEntity SesiAkademik;
}
