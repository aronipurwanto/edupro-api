/**
 * 
 */
package org.edupro.webapi.curriculum.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.base.model.BaseIdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_curriculum")
public class CurriculumEntity extends BaseIdEntity {
	@Column(name = "code", length = 20)
	private String code;
	
	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "position")
	private Integer position;

	@OneToMany(mappedBy = "curriculum")
	private List<AcademicSessionEntity> sesiAkademikList = new ArrayList<>();

	public CurriculumEntity(String code, String name, Integer position) {
		this.code = code;
		this.name = name;
		this.position = position;
	}
}
