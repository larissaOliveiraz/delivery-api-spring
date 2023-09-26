package com.sdeli.deliveryapi.exceptions.handler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("Resource not found.", "/resource-not-found"),
    INVALID_DATA("Invalid data.", "/invalid-data"),
    RESOURCE_ALREADY_EXISTS("Resource already exists.", "/resource-already-exists"),
    GENERAL_BUSINESS("General business exception.", "/general-business");

    private final String title;
    private final String path;

    ProblemType(String title, String path) {
        this.title = title;
        this.path = path;
    }

}
