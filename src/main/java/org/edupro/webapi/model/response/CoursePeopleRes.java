package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePeopleRes {
    private String courseId;
    private List<CoursePersonRes> teachers;
    private List<CourseSiswaRes> students;

    public CoursePeopleRes(String courseId) {
        this.courseId = courseId;
    }
}
