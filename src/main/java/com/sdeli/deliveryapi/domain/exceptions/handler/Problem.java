package com.sdeli.deliveryapi.domain.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private OffsetDateTime timestamp;
    private String type;
    private Integer status;
    private String title;
    private String detail;

    private List<Field> fields;

    @Getter
    @Builder
    public static class Field {
        private String name;
        private String message;
    }

}
