package com.payday.trade.emailNotification.service;

import com.payday.trade.emailNotification.entity.Users;
import com.payday.trade.emailNotification.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final UsersRepository usersRepository;

    @Override
    public void sendEmail(boolean result, Long userId) {

        Users users = usersRepository.findUserByUserId(userId);
        if (users != null && result == true) {
            String eusername = "express.test200@gmail.com";
            String password = "vbtkkfugiymajqip";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(eusername, password);
                        }
                    });
            session.setDebug(false);
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(eusername));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(users.getEmail()));
                message.setSubject("Success", "UTF-8");
                message.setText(users.getUsername() + "'s order is filled");
                Transport.send(message);
            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Not success");
        }
    }
}
