package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_COURSE_PERSON")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name="COURSEPRSID")),
        @AttributeOverride(name = "createdAt", column = @Column(name="COURSEPRSCRD")),
        @AttributeOverride(name = "createdBy", column = @Column(name="COURSEPRSCRUID")),
        @AttributeOverride(name = "updatedAt", column = @Column(name="COURSEPRSUPD")),
        @AttributeOverride(name = "updatedBy", column = @Column(name="COURSEPRSUPUID")),
        @AttributeOverride(name = "status", column = @Column(name="COURSEPRSSTAT"))
})
public class CoursePersonEntity extends BaseIdEntity{
    @Column(name = "COURSEID", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSEID", nullable = false)
    private CourseEntity course;

    @Column(name = "PERSONID", insertable = false, updatable = false)
    private String personId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSONID", nullable = false)
    private PersonEntity person;

    public CoursePersonEntity(CourseEntity course, PersonEntity person) {
        this.course = course;
        this.person = person;
    }
}
