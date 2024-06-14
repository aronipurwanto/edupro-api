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
@Table(name = "t_course")
public class CourseEntity extends BaseIdEntity {
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "short_name", nullable = false)
	private String shortName;
	
	@Column(name = "is_shown")
	private Boolean shown;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "image_id")
	private Long imageId;
	
	@Column(name = "format")
	private Integer format;
	
	@Column(name = "hidden_section")
	private Integer hiddenSection;
	
	@Column(name = "layout")
	private Integer layout;
	
	@Column(name = "is_complete_tracking")
	private Boolean completionTracking;
	
	/**
	 * kodeMapel dan kodeLevel diisi jika course/pembelajaran ini melekat di mapel
	 * pada level tertentu
	 */
	@Column(name = "mapel_id", length = 36)
	private String mapelId;

	@Column(name = "mapel_code", length = 10)
	private String kodeMapel;
	
	@Column(name = "level_id", length = 10)
	private String levelId;

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
