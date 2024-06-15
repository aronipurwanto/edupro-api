package org.edupro.webapi;

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
