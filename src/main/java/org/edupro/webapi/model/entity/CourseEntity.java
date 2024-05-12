/**
 * 
 */
package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Menyimpan data pembelajaran/course/classroom
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
@Table(name = "T_COURSE")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name="COURSEID")),
		@AttributeOverride(name = "createdAt", column = @Column(name="COURSECRD")),
		@AttributeOverride(name = "createdBy", column = @Column(name="COURSECRUID")),
		@AttributeOverride(name = "updatedAt", column = @Column(name="COURSEUPD")),
		@AttributeOverride(name = "updatedBy", column = @Column(name="COURSEUPUID")),
		@AttributeOverride(name = "status", column = @Column(name="COURSESTAT"))
})
public class CourseEntity extends BaseIdEntity {
	@Column(name = "COURSENM", nullable = false)
	private String name;

	@Column(name = "COURSEDESC", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "COURSENMSHORT", nullable = false)
	private String shortName;
	
	@Column(name = "COURSESHWN")
	private Boolean shown;
	
	@Column(name = "COURSESTARTD")
	private LocalDate startDate;
	
	@Column(name = "COURSEENDD")
	private LocalDate endDate;
	
	@Column(name = "COURSESMRY")
	private String summary;
	
	@Column(name = "COURSEIMGID")
	private Long imageId;
	
	@Column(name = "COURSEFMT")
	private Integer format;
	
	@Column(name = "COURSEHIDDNSECT")
	private Integer hiddenSection;
	
	@Column(name = "COURSELYOT")
	private Integer layout;
	
	@Column(name = "COURSECMPLTTRCK")
	private Boolean completionTracking;
	
	/**
	 * kodeMapel dan kodeLevel diisi jika course/pembelajaran ini melekat di mapel
	 * pada level tertentu
	 */
	@Column(name = "MAPELID", length = 36)
	private String mapelId;

	@Column(name = "MAPELKD", length = 10)
	private String kodeMapel;
	
	@Column(name = "LVLKD", length = 10)
	private String kodeLevel;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CourseResourceEntity> courseResourceList = new ArrayList<>();

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CourseSectionEntity> courseSectionList = new ArrayList<>();

	public CourseEntity(String name, String description, String shortName, LocalDate startDate, LocalDate endDate, String createdBy) {
		this.name = name;
		this.description = description;
		this.shortName = shortName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.setCreatedBy(createdBy);
	}

	public void addCourseResource(CourseResourceEntity courseResource) {
		courseResourceList.add(courseResource);
		courseResource.setCourse(this);
	}

	public void addCourseSection(CourseSectionEntity courseSection) {
		courseSectionList.add(courseSection);
		courseSection.setCourse(this);
	}
}
