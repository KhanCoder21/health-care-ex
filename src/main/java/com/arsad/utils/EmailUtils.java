package com.arsad.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/* Created by Arsad on 2023-03-20 23:12 */
@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMail(String from, String[] to, String[] cc, String[] bcc, String subject, String body, Resource[] attachments) {
        boolean isMailSent;
        try {
            /* create empty message body */
            MimeMessage message = mailSender.createMimeMessage();
            /* fill details like message body and attachment exist or not */
            MimeMessageHelper helper = new MimeMessageHelper(message, attachments != null && attachments.length > 0);
            helper.setFrom(from);
            helper.setTo(to);
            if (cc != null)
                helper.setCc(cc);
            if (bcc != null)
                helper.setBcc(bcc);
            if (null != body)
                helper.setText(body);
            if (null != attachments) {
                for (Resource attachment : attachments) {
                    helper.addAttachment(Objects.requireNonNull(attachment.getFilename()), attachment);
                }
            }
            mailSender.send(message);
            isMailSent = true;
        } catch (Exception e) {
            e.printStackTrace();
            isMailSent = false;
        }
        return isMailSent;
    }

    /* Keeping overloaded methods */
    public boolean sendMail(String from, String[] to, String[] cc, String subject, String body, Resource[] attachments) {
        return sendMail(from, to, cc, null, subject, body, attachments);
    }

    public boolean sendMail(String from, String[] to, String subject, String body, Resource[] attachments) {
        return sendMail(from, to, null, null, subject, body, attachments);
    }

    public boolean sendMail(String from, String[] to, String subject, String body) {
        return sendMail(from, to, null, null, subject, body, null);
    }

    public boolean sendMail(String from, String to, String subject, String body, Resource[] attachments) {
        return sendMail(from, new String[]{to}, null, null, subject, body, attachments);
    }

}
