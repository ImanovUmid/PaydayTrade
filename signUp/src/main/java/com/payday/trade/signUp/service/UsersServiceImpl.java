package com.payday.trade.signUp.service;

import com.payday.trade.signUp.entity.Users;
import com.payday.trade.signUp.entity.UsersCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.payday.trade.signUp.repository.UsersCheckRepository;
import com.payday.trade.signUp.repository.UsersRepository;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersCheckRepository usersCheckRepository;

    @Override
    public void createUser(String userName, String password, String email) {
        Users users = new Users();
        UsersCheck usersCheck = new UsersCheck();
        String key = generateRandomString();
        Optional<Users> usersOptional = usersRepository.findUserByUserOptionalName(userName);
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("Already registered user");
        } else {
            if (password.length() >= 6 && password.matches("^[a-zA-Z0-9]*$")) {
                users.setUsername(userName);
                users.setPassword(password);
                users.setEmail(email);
                users.setStatus(0);
                usersRepository.save(users);
                usersCheck.setUsername(userName);
                usersCheck.setSessionKey(key);
                usersCheckRepository.save(usersCheck);
                sendEmail(key, userName, email);
            } else {
                throw new IllegalStateException("Only allow passwords with 6 or more alphanumeric characters");
            }
        }
    }

    public String generateRandomString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public int sendEmail(String sessionKey, String username, String recipient) {

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

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Activation", "UTF-8");
            message.setText("------ \n"
                    + "Username:  " + username + " \n"
                    + "Date: " + new Date() + " \n\n"
                    + "Click the link and activate the account.  \n"
                    + "Sign link: http://localhost:8080/signUpAPI/userActivate/" + sessionKey, "UTF-8");

            Transport.send(message);

            return 1;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
