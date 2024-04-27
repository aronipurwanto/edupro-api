/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Level ini menunjukkan tingkatan kelas
 * Contoh level I, II, III, dst
 * 
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_LEVEL")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="LVLID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="LVLCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="LVLCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="LVLUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="LVLUID")),
		@AttributeOverride(name = "status", column = @Column(name="LVLSTAT"))
})
public class LevelEntity extends BaseIdEntity {
	private static final long serialVersionUID = -1692222214526057221L;

	@Column(name = "LBGID", nullable = false)
	private Integer idLembaga;

	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kode;

	@Column(name = "LVLNM", length = 10, nullable = false)
	private String nama;

	@Column(name = "LVLURUT")
	private Integer noUrut;
}
