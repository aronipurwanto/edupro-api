/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="CRSSECID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="CRSSECCRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="CRSSECCRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="CRSSECUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="CRSSECUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="CRSSECSTAT"))
})
public class CourseSectionEntity extends BaseEntity {
	@Id
	@Column(name = "SECID", nullable = false, length = 36)
	private String id;
	
	@Column(name = "COURSEID", nullable = false)
	private String courseId;

	@ManyToOne
	@JoinColumn(name = "COURSEID", insertable = false, updatable = false)
	private CourseEntity course;

	@Column(name = "SECTYPE", length = 20, nullable = false)
	private String sectionType;
	
	@Column(name = "SECNAME", length = 100, nullable = false)
	private String name;

	@Column(name = "SECDESC", nullable = false)
	private String description;

	@Column(name = "PARENTID", length = 36)
	private String parentId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PARENTID", insertable = false, updatable = false)
	private CourseSectionEntity parent;

	@OneToMany(mappedBy = "parent")
	private Set<CourseSectionEntity> children = new HashSet<>();

	@Column(name = "NOURUT")
	private Integer noUrut;
}
