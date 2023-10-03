package com.sdeli.deliveryapi.infra.service.mail;

import com.sdeli.deliveryapi.core.mail.MailProperties;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class MockSendMailService extends SmtpSendMailService {

    public MockSendMailService(MailProperties mailProperties, JavaMailSender mailSender, Configuration freemarkerConfig) {
        super(mailSender, mailProperties, freemarkerConfig);
    }

    @Override
    public void send(Message message) {
        String content = processTemplate(message);

        log.info("MOCK EMAIL to: {}\n{}", message.getRecipients(), content);
    }

}
