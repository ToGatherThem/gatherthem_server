package fr.gatherthem.gatherthem_server.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class Mail {
    @Autowired
    private Environment env;
    public void sendMail(String to, String subject, String body) {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", env.getProperty("spring.mail.host"));
        properties.put("mail.smtp.port", env.getProperty("spring.mail.port"));
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(env.getProperty("spring.mail.username"), env.getProperty("spring.mail.password"));
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(env.getProperty("spring.mail.username"));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new javax.mail.internet.InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);
            javax.mail.Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
