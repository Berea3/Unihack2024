package com.unihackback.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class Email {

    @Autowired
    private final JavaMailSender javaMailSender=new JavaMailSenderImpl();

//    public Email(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }

    public Email() {}

    public void send(String toEmail, String subject, String body){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("your_email@example.com"); // Replace with your sending email
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MailException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
            throw ex; // Re-throw the exception for proper handling by the client
        }
    }
}
