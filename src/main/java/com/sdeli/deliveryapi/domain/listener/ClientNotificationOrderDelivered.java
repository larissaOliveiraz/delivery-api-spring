package com.sdeli.deliveryapi.domain.listener;

import com.sdeli.deliveryapi.domain.event.OrderDeliveredEvent;
import com.sdeli.deliveryapi.domain.model.Order;
import com.sdeli.deliveryapi.domain.services.SendMailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@AllArgsConstructor
@Component
public class ClientNotificationOrderDelivered {

    private final SendMailService sendMailService;

    @TransactionalEventListener
    public void whenOrderDelivered(OrderDeliveredEvent event) {
        Order order = event.getOrder();

        var message = SendMailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Order delivered.")
                .content("order-delivered.html")
                .recipient(order.getClient().getEmail())
                .variable("order", order)
                .build();

        sendMailService.send(message);
    }

}
