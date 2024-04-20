package org.edupro.webapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionReq {
    private String id;
    private String courseId;
    private String name;
    private String description;
}
