package com.sdeli.deliveryapi.core.mail;

import com.sdeli.deliveryapi.domain.services.SendMailService;
import com.sdeli.deliveryapi.infra.service.mail.MockSendMailService;
import com.sdeli.deliveryapi.infra.service.mail.SandBoxMailService;
import com.sdeli.deliveryapi.infra.service.mail.SmtpSendMailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
@Configuration
public class MailConfig {

    private final MailProperties mailProperties;
    private final JavaMailSender mailSender;
    private final freemarker.template.Configuration freemarkerConfig;

    @Bean
    public SendMailService sendMailService() {
        switch (mailProperties.getImpl()) {
            case "mock" -> {
                return new MockSendMailService(mailProperties, mailSender, freemarkerConfig);
            }
            case "smtp" -> {
                return new SmtpSendMailService(mailSender, mailProperties, freemarkerConfig);
            }
            case "sandbox" -> {
                return new SandBoxMailService(mailSender, mailProperties, freemarkerConfig);
            }
            default -> {
                return null;
            }
        }
    }

}
