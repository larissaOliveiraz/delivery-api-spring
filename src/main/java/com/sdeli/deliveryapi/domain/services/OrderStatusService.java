package com.sdeli.deliveryapi.domain.services;

import com.sdeli.deliveryapi.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OrderStatusService {

    private final OrderService service;
    private final SendMailService sendMailService;

    @Transactional
    public void confirm(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.confirm();

        var message = SendMailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed.")
                .content("order-confirmed.html")
                .recipient(order.getClient().getEmail())
                .variable("order", order)
                .build();

        sendMailService.send(message);
    }

    @Transactional
    public void deliver(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.deliver();
    }

    @Transactional
    public void cancel(String code) {
        Order order = service.findByCodeOrThrow(code);
        order.cancel();
    }

}
