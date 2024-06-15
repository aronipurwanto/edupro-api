/**
 * 
 */
package org.edupro.webapi.academic.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.BaseIdEntity;
import org.edupro.webapi.curriculum.model.CurriculumEntity;

import java.time.LocalDate;

/**
 * Data semester
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
@Table(name = "t_sesi_akademik")
public class AcademicSessionEntity extends BaseIdEntity {
	@Column(name = "academic_year_id", length = 36, nullable = false)
	private String academicYearId;

	@ManyToOne
	@JoinColumn(name = "academic_year_id",insertable = false, updatable = false)
	private AcademicYearEntity academicYear;

	@Column(name = "curriculum_id", length = 36, nullable = false)
	private String curriculumId;

	@ManyToOne
	@JoinColumn(name = "curriculum_id", insertable = false, updatable = false)
	private CurriculumEntity curriculum;

	@Column(name = "semester", nullable = false)
	private Integer semester; // 1 = ganjil, 2 = genap

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;
}