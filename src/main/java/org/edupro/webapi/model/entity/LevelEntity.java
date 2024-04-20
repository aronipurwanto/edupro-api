/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

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
public class LevelEntity extends BlankBaseEntity {
	private static final long serialVersionUID = -1692222214526057221L;

	@Id
	@Column(name = "LVID",length = 36, nullable = false)
	private String id;

	@Column(name = "LBGID", nullable = false)
	private Integer idLembaga;

	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kode;

	@Column(name = "LVLNM", length = 10, nullable = false)
	private String nama;

	@Column(name = "LVLURUT")
	private Integer noUrut;
	
	@Default
	@Column(name = "LVLSTAT", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.AKTIF;
}
