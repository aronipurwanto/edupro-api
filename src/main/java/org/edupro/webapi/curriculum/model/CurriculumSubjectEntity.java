/**
 * 
 */
package org.edupro.webapi.curriculum.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.institution.InstitutionEntity;
import org.edupro.webapi.BaseIdEntity;
import org.edupro.webapi.subject.SubjectEntity;

/**
 * Detail mata pelajaran per kurikulum
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
@Table(name = "t_curriculum_subject")
public class CurriculumSubjectEntity extends BaseIdEntity {
	@Column(name = "institution_id", nullable = false, length = 36)
	private String institutionId;

	@ManyToOne
	@JoinColumn(name = "institution_id", insertable = false, updatable = false)
	private InstitutionEntity institution;

	@Column(name = "subject_id", nullable = false, length = 36)
	private String subjectId;

	@ManyToOne
	@JoinColumn(name = "subject_id", insertable = false, updatable = false)
	private SubjectEntity subject;

	@Column(name = "code", nullable = false, length = 20)
	private String code;
}
