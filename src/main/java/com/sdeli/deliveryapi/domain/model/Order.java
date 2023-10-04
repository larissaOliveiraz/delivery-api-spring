package com.sdeli.deliveryapi.domain.model;

import com.sdeli.deliveryapi.domain.event.OrderCancelledEvent;
import com.sdeli.deliveryapi.domain.event.OrderConfirmedEvent;
import com.sdeli.deliveryapi.domain.event.OrderDeliveredEvent;
import com.sdeli.deliveryapi.domain.exceptions.GeneralBusinessException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity(name = "orders")
public class Order extends AbstractAggregateRoot<Order> {

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

    public void confirm() {
        setStatus(OrderStatus.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());

        registerEvent(new OrderConfirmedEvent(this));
    }

    public void deliver() {
        setStatus(OrderStatus.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());

        registerEvent(new OrderDeliveredEvent(this));
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELLED);
        setCancellationDate(OffsetDateTime.now());

        registerEvent(new OrderCancelledEvent(this));
    }

    public void setStatus(OrderStatus newStatus) {
        if (getStatus().statusNotAllowed(newStatus)) {
            throw new GeneralBusinessException(String.format(
                    "Order with id %s cannot change status from %s to %s",
                    getCode(), getStatus(), newStatus
            ));
        }

        this.status = newStatus;
    }

    @PrePersist
    public void generateCode() {
        setCode(UUID.randomUUID().toString());
    }

}
