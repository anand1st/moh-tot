package org.mysj.service;

import org.mysj.models.EmailMessage;

public interface MailSender {

    void sendMail(EmailMessage emailMessage);
}
