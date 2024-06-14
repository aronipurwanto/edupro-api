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
@Table(name = "t_course_resource")
public class CourseResourceEntity extends BaseIdEntity {
	@Column(name = "course_section_id", nullable = false, length = 36)
	private String courseSectionId;

	@ManyToOne
	@JoinColumn(name = "course_section_id", insertable = false, updatable = false)
	private CourseSectionEntity courseSection;

	/**
	 * Mengacu pada google classroom, jenis resource ini contohnya: assignment, quiz, question, material
	 */
	@Column(name = "type", nullable = false)
	private Integer type;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "attachment_id")
	private String attachmentId;

	@ManyToOne
	@JoinColumn(name = "attachment_id", insertable = false, updatable = false)
	private AttachmentEntity attachment;

	public CourseResourceEntity(String name, String courseSectionId) {
		this.name = name;
		this.courseSectionId = courseSectionId;
	}
}
