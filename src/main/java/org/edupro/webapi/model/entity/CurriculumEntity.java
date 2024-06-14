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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_kurkulum")
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
