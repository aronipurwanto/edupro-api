package org.edupro.webapi.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int statusCode;
    private Object message;
    private T data;
}
