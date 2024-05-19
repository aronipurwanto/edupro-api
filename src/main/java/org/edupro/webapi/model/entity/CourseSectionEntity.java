/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
public class CourseSectionEntity extends BaseIdEntity {
	@Column(name = "COURSEID", insertable = false, updatable = false)
	private String courseId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSEID", nullable = false)
	private CourseEntity course;

	@Column(name = "SECTYPE", length = 20, nullable = false)
	private String sectionType;
	
	@Column(name = "SECNAME", length = 100, nullable = false)
	private String name;

	@Column(name = "SECDESC")
	private String description;

	@Column(name = "PARENTID", length = 36, insertable = false, updatable = false)
	private String parentId;

	@ManyToOne
	@JoinColumn(name = "PARENTID")
	private CourseSectionEntity parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<CourseSectionEntity> children = new ArrayList<>();

	@Column(name = "NOURUT")
	private Integer noUrut;

	public CourseSectionEntity(String id, CourseEntity course, String sectionType, String name, Integer noUrut) {
		this.id = id;
		this.course = course;
		this.sectionType = sectionType;
		this.name = name;
		this.noUrut = noUrut;
	}

	public CourseSectionEntity(String id, CourseEntity course, String sectionType, String name, Integer noUrut, CourseSectionEntity parent) {
		this.id = id;
		this.course = course;
		this.sectionType = sectionType;
		this.name = name;
		this.noUrut = noUrut;
		this.parent = parent;
	}

	public void addChild(CourseSectionEntity courseSectionEntity) {
		this.children.add(courseSectionEntity);
		courseSectionEntity.setParent(this);
	}
}
