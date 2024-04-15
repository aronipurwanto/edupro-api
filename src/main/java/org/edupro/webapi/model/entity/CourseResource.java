/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_COURSE_RESOURCE")
public class CourseResource extends BaseEntity {
	@Id
	@Column(name = "CRSRCID", nullable = false)
	private Long id;
	
	@Column(name = "COURSEID", nullable = false)
	private Long courseId;
	
	@Column(name = "CRSSECID")
	private Integer courseSectionId;


	@Column(name = "RSRCID", nullable = false)
	private Long resourceId;
}
