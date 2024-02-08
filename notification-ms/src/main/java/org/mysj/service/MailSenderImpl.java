package org.mysj.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.mysj.models.EmailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@lombok.RequiredArgsConstructor
@Service
class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(EmailMessage emailMessage) {
        try {
            var message = mailSender.createMimeMessage();
            message.setFrom("admin@mysj.com");
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailMessage.getRecipient()));
            message.setSubject(emailMessage.getSubject());
            message.setContent(emailMessage.getContent(), "text/html; charset=utf-8");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
