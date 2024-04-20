package org.edupro.webapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentRes {
    private String id;
    private String contentType;
    private String filename;
    private boolean publiclyAccessible = true;
    private String filePath;
    private Long size;
    private LocalDateTime createdAt;
    private String createdBy;
    private String description;
    private String owner;
}
