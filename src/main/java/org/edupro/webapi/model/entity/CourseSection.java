/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Menyimpan topic/section dari suatu course/classroom
 * 
 * 
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
@Table(name = "T_COURSE_SECTION")
public class CourseSection extends BaseEntity {
	@Id
	@Column(name = "CRSSECID", nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "COURSESECTION")
	@TableGenerator(name = "COURSESECTION", table = "T_SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "COURSESECTION", valueColumnName = "SEQ_VAL", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Column(name = "COURSEID", nullable = false)
	private Integer courseId;
	
	@Column(name = "CRSSECNM", length = 100, nullable = false)
	private String name;

	@Column(name = "CRSSECDESC", nullable = false)
	private String description;
}
