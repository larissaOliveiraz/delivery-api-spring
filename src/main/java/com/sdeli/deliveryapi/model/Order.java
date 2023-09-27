package com.sdeli.deliveryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "orders")
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private BigDecimal subtotal;
    private BigDecimal shipment;
    private BigDecimal total;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @CreationTimestamp
    private OffsetDateTime creationDate;

    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.MERGE)
    private List<OrderItem> items = new ArrayList<>();

    public void calculateOrderTotal() {
        getItems().forEach(OrderItem::calculateItemTotal);

        this.subtotal = getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.setTotal(this.subtotal.add(this.shipment));
    }

    @PrePersist
    public void generateCode() {
        setCode(UUID.randomUUID().toString());
    }

}
