package org.edupro.webapi.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.model.entity.CourseEntity;
import org.edupro.webapi.model.entity.CourseSectionEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResouceReq {
    private String id;
    private String courseId;
    private String courseSectionId;
    private Integer type;
    private String name;
    private String attachmentId;
}
