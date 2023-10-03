package com.sdeli.deliveryapi.infra.service.mail;

import com.sdeli.deliveryapi.core.mail.MailProperties;
import com.sdeli.deliveryapi.domain.services.SendMailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


@AllArgsConstructor
@Service
public class SmptSendMailService implements SendMailService {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;
    private final Configuration freemarkerConfig;



    @Override
    public void send(Message message) {
        try {
            String content = processTemplate(message);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            mimeHelper.setFrom(mailProperties.getFrom());
            mimeHelper.setTo(message.getRecipients().toArray(new String[0]));
            mimeHelper.setSubject(message.getSubject());
            mimeHelper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new MailException("Failed to send email." + ex.getMessage(), ex);
        }

    }

    private String processTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getContent());
            return FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, message.getVariables());
        } catch (Exception ex) {
            throw new MailException("Failed to create email template." + ex.getMessage(), ex);
        }
    }

}
