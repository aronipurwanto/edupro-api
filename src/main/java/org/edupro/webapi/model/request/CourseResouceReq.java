package org.edupro.webapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResouceReq {
    private String id;
    @NotEmpty
    @Size(min = 32, max = 36, message = "courseId minimal 32 dan maximal 36")
    private String courseId;
    @NotEmpty
    @Size(min = 32, max = 36, message = "courseSectionId minimal 32 dan maximal 36")
    private String courseSectionId;
    private Integer type;
    private String name;
    private String attachmentId;
}
