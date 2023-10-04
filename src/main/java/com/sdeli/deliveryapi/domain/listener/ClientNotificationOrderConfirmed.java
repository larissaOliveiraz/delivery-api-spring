package com.sdeli.deliveryapi.domain.listener;

import com.sdeli.deliveryapi.domain.event.OrderConfirmedEvent;
import com.sdeli.deliveryapi.domain.model.Order;
import com.sdeli.deliveryapi.domain.services.SendMailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@AllArgsConstructor
@Component
public class ClientNotificationOrderConfirmed {

    private final SendMailService sendMailService;

    @TransactionalEventListener
    public void whenOrderConfirmed(OrderConfirmedEvent event) {
        Order order = event.getOrder();

        var message = SendMailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order confirmed.")
                .content("order-confirmed.html")
                .recipient(order.getClient().getEmail())
                .variable("order", order)
                .build();

        sendMailService.send(message);
    }

}
