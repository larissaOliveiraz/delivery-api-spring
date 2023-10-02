package com.sdeli.deliveryapi.domain.repositories;

import com.sdeli.deliveryapi.domain.model.Product;
import com.sdeli.deliveryapi.domain.model.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends
        JpaRepository<Product, Long>,
        ProductRepositoryQueries {

    @Query("from products where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    @Query("select photo from product_photo photo join photo.product product " +
            "where product.restaurant.id = :restaurantId and photo.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}
