package com.sdeli.deliveryapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "product_photo")
public class ProductPhoto {

    @Id
    @EqualsAndHashCode.Include
    private Long productId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;

    private String filename;
    private String description;
    private String contentType;
    private Long size;

}
