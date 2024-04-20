/**
 * 
 */
package org.edupro.webapi.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class CourseEntity extends BaseEntity {
	@Id
	@Column(name = "COURSEID", nullable = false, length = 36)
	private String id;

	@Column(name = "COURSENM", nullable = false)
	private String name;
	
	@Column(name = "COURSENMSHORT", nullable = false)
	private String shortName;
	
	@Column(name = "COURSESHWN")
	private Boolean shown;
	
	@Column(name = "COURSESTARTD")
	private LocalDateTime startDate;
	
	@Column(name = "COURSEENDD")
	private LocalDateTime endDate;
	
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
	@Column(name = "MAPELID", length = 10, nullable = false)
	private String mapelId;

	@ManyToOne
	@JoinColumn(name = "MAPELID", insertable = false, updatable = false)
	private MapelEntity mapel;

	@Column(name = "MAPELKD", length = 10, nullable = false)
	private String kodeMapel;
	
	@Column(name = "LVLKD", length = 10, nullable = false)
	private String kodeLevel;

	@OneToMany(mappedBy = "course")
	private List<CourseResourceEntity> courseResourceList = new ArrayList<>();

	@OneToMany(mappedBy = "course")
	private List<CourseSectionEntity> courseSectionList = new ArrayList<>();
}
