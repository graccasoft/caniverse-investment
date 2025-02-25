package io.caniverse.investment.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class EmailSenderService {
        private final JavaMailSender emailSender;
        private final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

        public EmailSenderService(JavaMailSender emailSender) {
                this.emailSender = emailSender;
        }

        @Value("${spring.mail.username}")
        private String senderEmail;

        @Async
        public void sendEmail(String emailDest,
                              String subject,
                              String body) {
                sendEmailViaGmail(emailDest,subject,body, null);

        }
        public void sendEmail(String emailDest,
                              String subject,
                              String body,
                              ArrayList<String> attachments) {
                sendEmailViaGmail(emailDest,subject,body, attachments);

        }

        private void sendEmailViaGmail(String emailDest,
                                 String subject,
                                 String body,
                                 ArrayList<String> attachments)  {

                try {
                        MimeMessage mimeMessage = emailSender.createMimeMessage();
                        var simpleMailMessage = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                        simpleMailMessage.setFrom(senderEmail);
                        simpleMailMessage.setTo(emailDest);
                        simpleMailMessage.setSubject(subject);
                        simpleMailMessage.setText(body, true);

                        if (attachments != null) {
                                attachments.forEach(attachment -> {
                                        try {
                                                File attachmentFile = new File(attachment);
                                                simpleMailMessage.addAttachment(attachmentFile.getName(), attachmentFile);

                                        } catch (MessagingException ex) {
                                                log.info("Failed to add attachment", ex);
                                        }
                                });

                        }
                        emailSender.send(mimeMessage);
                }catch (MessagingException ex){
                        log.error("Failed to send email", ex);
                }
        }
}
