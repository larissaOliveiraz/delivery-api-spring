package com.sdeli.deliveryapi.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoDTO {

    private String filename;
    private String description;
    private String contentType;
    private Long size;

}
