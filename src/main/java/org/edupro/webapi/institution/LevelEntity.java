/**
 * 
 */
package org.edupro.webapi.institution;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.BaseIdEntity;

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
@Table(name = "t_level")
public class LevelEntity extends BaseIdEntity {
	@Column(name = "institution_id", nullable = false)
	private String institutionId;

	@ManyToOne
	@JoinColumn(name = "institution_id", insertable = false, updatable = false)
	private InstitutionEntity institution;

	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "position")
	private Integer position;

	public LevelEntity(String institutionId, String code, String nama, Integer position) {
		this.position = position;
		this.name = nama;
		this.code = code;
		this.institutionId = institutionId;
	}
}