/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
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
@Table(name = "t_academic_year")
public class AcademicYearEntity extends BaseIdEntity {
	private static final long serialVersionUID = 6154593684680418364L;
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "kurikulum_id", length = 36, nullable = false)
	private String kurikulumId;
	
	@Column(name = "kurikulum_id", length = 20, nullable = false)
	private String kodeKurikulum;

	@OneToMany(mappedBy = "tahunAjaran")
	private List<AcademicSessionEntity> sesiAkademikList = new ArrayList<>();

	public AcademicYearEntity(String name, String kurikulumId, String kodeKurikulum) {
		this.name = name;
		this.kurikulumId = kurikulumId;
		this.kodeKurikulum = kodeKurikulum;
	}
}
