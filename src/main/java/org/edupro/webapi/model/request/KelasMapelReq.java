package org.edupro.webapi.model.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.model.entity.KelasEntity;
import org.edupro.webapi.model.entity.MapelEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasMapelReq {
    private String id;
    private String kelasId;
    private String mapelId;
    private double nilaiKKM;
}
