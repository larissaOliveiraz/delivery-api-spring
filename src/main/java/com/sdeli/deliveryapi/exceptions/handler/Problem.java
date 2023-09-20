package com.sdeli.deliveryapi.exceptions.handler;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class Problem {

    private OffsetDateTime timestamp;
    private String type;
    private Integer status;
    private String title;
    private String detail;

}
