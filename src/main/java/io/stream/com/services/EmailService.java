package io.stream.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.stream.com.models.dtos.ContactFormDto;
import io.stream.com.utils.EmailUtil;

@Service
public class EmailService { 

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerification(String email, String token){
        mailSender.send(EmailUtil.createVerifyingEmail(email, token));
    }

    public void sendContactForm(ContactFormDto contactFormDto){
        mailSender.send(EmailUtil.createContactForm(contactFormDto.getSubject(), contactFormDto.getFullName(), contactFormDto.getBody(), contactFormDto.getEmail()));
    }
}