package com.project.heroes.infra.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.to}")
    private String to;

    @Value("${spring.mail.from}")
    private String from;

    public void sendSimpleEmail(String subject,
                                String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setFrom(from);

            Context context = new Context();
            context.setVariable("title", subject);
            context.setVariable("content", text);

            String htmlContent = templateEngine.process("email-template", context);

            helper.setSubject(subject);
//            helper.setText(text, false); // HTML 아님 (일반 텍스트)
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("[ERROR] ", e);
        }
    }
}
