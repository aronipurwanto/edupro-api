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
public class CourseSiswaEntity extends BaseIdEntity{
    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "siswa_id", insertable = false, updatable = false)
    private String siswaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siswa_id", nullable = false)
    private StudentEntity siswa;

    public CourseSiswaEntity(CourseEntity course, StudentEntity siswa) {
        this.course = course;
        this.siswa = siswa;
    }
}
