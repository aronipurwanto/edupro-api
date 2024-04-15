package org.edupro.webapi.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError<T> {
    private int statusCode;
    private String message;
    private Object errors;
}
