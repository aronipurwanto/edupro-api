/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
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
public class CourseResourceEntity extends BaseEntity {
	@Id
	@Column(name = "CRSRCID", nullable = false, length = 36)
	private String id;
	
	@Column(name = "COURSEID", nullable = false, length = 36)
	private String courseId;

	@ManyToOne
	@JoinColumn(name = "COURSEID", insertable = false, updatable = false)
	private CourseEntity course;
	
	@Column(name = "CRSSECID", nullable = false, length = 36)
	private String courseSectionId;

	@ManyToOne
	@JoinColumn(name = "CRSSECID", insertable = false, updatable = false)
	private CourseSectionEntity courseSection;

	/**
	 * Mengacu pada google classroom, jenis resource ini contohnya: assignment, quiz, question, material
	 */
	@Column(name = "RSRCTPE", nullable = false)
	private Integer type;

	@Column(name = "RSRCNM", nullable = false)
	private String name;

	@Column(name = "ATTCHID")
	private String attachmentId;

	@ManyToOne
	@JoinColumn(name = "ATTCHID", insertable = false, updatable = false)
	private AttachmentEntity attachment;
}
