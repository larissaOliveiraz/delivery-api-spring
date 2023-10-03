package com.sdeli.deliveryapi.core.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("delivery.mail")
public class MailProperties {

    private String impl;

    private String from;

    private Sandbox sandbox = new Sandbox();

    @Getter
    @Setter
    public static class Sandbox {
        private String recipient;
    }

}
