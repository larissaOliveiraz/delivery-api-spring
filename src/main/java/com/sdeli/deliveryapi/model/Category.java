package com.sdeli.deliveryapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "categories")
public class Category {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    private String name;

}
