package com.sdeli.deliveryapi.domain.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface SendMailService {

    void send(Message message);

    @Getter
    @Builder
    class Message {

        @Singular("recipient")
        private Set<String> recipients;

        private String subject;

        private String content;

        @Singular("variable")
        private Map<String, Object> variables;

    }

}
