package com.sdeli.deliveryapi.domain.repositories;

import com.sdeli.deliveryapi.domain.model.ProductPhoto;

public interface ProductRepositoryQueries {

    ProductPhoto save(ProductPhoto photo);

    void delete(ProductPhoto photo);

}
