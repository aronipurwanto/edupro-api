/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Master data mapel per level
 * 
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
@Table(name = "T_MAPEL_LEVEL")
@AttributeOverrides({
	@AttributeOverride(name = "createdAt", column = @Column(name="MAPLVLCRD")),
	@AttributeOverride(name = "createdBy", column = @Column(name="MAPLVLCRUID")),
	@AttributeOverride(name = "updatedAt", column = @Column(name="MAPLVLUPD")),
	@AttributeOverride(name = "updatedBy", column = @Column(name="MAPLVLUPUID"))
})
public class MapelLevelEntity extends BaseEntity {
	private static final long serialVersionUID = -4046075931995216469L;
	@Id
	@Column(name = "MAPLVID", length = 36, nullable = false)
	private String id;

	@Column(name = "LBGID", nullable = false)
	private String idLembaga;

	@ManyToOne
	@JoinColumn(name = "LBGID", insertable = false, updatable = false)
	private LembagaEntity lembaga;

	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kodeLevel;

	@Column(name = "MAPELKD", length = 10, nullable = false)
	private String kodeMapel;

	/**
	 * 0 = semua sesi (ganjil dan genap)
	 * 1 = semester ganjil saja
	 * 2 = semester genap saja
	 */
	@Column(name = "SAURUT")
	private Integer noUruSesiAkademik;
	
	@Column(name = "MAPLVLKKM")
	private double nilaiKKM;
}
