package com.fleetmanagement.api_rest.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public String sendMessageWithAttachment(
            String to,
            String subject,
            String text) throws MessagingException {


        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(username);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        String pathToAttachment = "temp.xlsx";
        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Trajectories.xlsx", file);

        javaMailSender.send(message);
        return pathToAttachment;
    }
}
