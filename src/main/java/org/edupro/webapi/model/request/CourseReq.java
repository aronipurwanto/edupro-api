package org.edupro.webapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReq {
    private String id;
    private String name;
    private String shortName;
    private Boolean shown;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String mapelId;
    private String kodeMapel;
    private String kodeLevel;
}
