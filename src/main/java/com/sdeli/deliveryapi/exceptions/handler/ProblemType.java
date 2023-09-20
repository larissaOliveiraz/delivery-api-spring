package com.sdeli.deliveryapi.exceptions.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("Resource not found", "/resource-not-found");

    private final String title;
    private final String path;

    ProblemType(String title, String path) {
        this.title = title;
        this.path = path;
    }

}
