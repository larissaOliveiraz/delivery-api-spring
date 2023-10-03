package com.sdeli.deliveryapi.infra.service.mail;

import com.sdeli.deliveryapi.core.mail.MailProperties;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SandBoxMailService extends SmtpSendMailService {

    private final MailProperties mailProperties;

    public SandBoxMailService(JavaMailSender mailSender, MailProperties mailProperties, Configuration freemarkerConfig) {
        super(mailSender, mailProperties, freemarkerConfig);
        this.mailProperties = mailProperties;
    }

    @Override
    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);

        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage);
        mimeHelper.setTo(mailProperties.getSandbox().getRecipient());

        return mimeMessage;
    }
}
