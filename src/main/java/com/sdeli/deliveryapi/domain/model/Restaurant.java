package com.sdeli.deliveryapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "restaurants")
public class Restaurant {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal shipment;

    private Boolean active = Boolean.TRUE;
    private Boolean open = Boolean.FALSE;

    @Embedded
    private Address address;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "restaurants_payment_types",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_type_id")
    )
    private Set<PaymentType> paymentTypes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "restaurants_users_responsible",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    public boolean acceptPaymentType(PaymentType paymentType) {
        return getPaymentTypes().contains(paymentType);
    }

    public void addPaymentType(PaymentType paymentType) {
        getPaymentTypes().add(paymentType);
    }

    public void removePaymentType(PaymentType paymentType) {
        getPaymentTypes().remove(paymentType);
    }

    public void addUserResponsible(User user) {
        getUsers().add(user);
    }

    public void removeUserResponsible(User user) {
        getUsers().remove(user);
    }

    public void activate() {
        this.setActive(true);
    }

    public void deactivate() {
        this.setActive(false);
    }

    public void open() {
        this.setOpen(true);
    }

    public void close() {
        this.setOpen(false);
    }

}
