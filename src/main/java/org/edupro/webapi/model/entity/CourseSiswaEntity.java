package org.edupro.webapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_COURSE_SISWA")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name="COURSESWID")),
        @AttributeOverride(name = "createdAt", column = @Column(name="COURSESWCRD")),
        @AttributeOverride(name = "createdBy", column = @Column(name="COURSESWCRUID")),
        @AttributeOverride(name = "updatedAt", column = @Column(name="COURSESWUPD")),
        @AttributeOverride(name = "updatedBy", column = @Column(name="COURSESWUPUID")),
        @AttributeOverride(name = "status", column = @Column(name="COURSESWSTAT"))
})
public class CourseSiswaEntity extends BaseIdEntity{
    @Column(name = "COURSEID", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSEID", nullable = false)
    private CourseEntity course;

    @Column(name = "SISWAID", insertable = false, updatable = false)
    private String siswaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SISWAID", nullable = false)
    private SiswaEntity siswa;

    public CourseSiswaEntity(CourseEntity course, SiswaEntity siswa) {
        this.course = course;
        this.siswa = siswa;
    }
}
