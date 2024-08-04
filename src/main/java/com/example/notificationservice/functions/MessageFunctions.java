package com.example.notificationservice.functions;

import com.example.notificationservice.configs.EmailUtil;
import com.example.notificationservice.dtos.SendEmailDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private EmailUtil emailUtil;

    public MessageFunctions(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @Bean
    public Function<SendEmailDto,String> email(){
        return sendEmailDto -> {
            Properties props = new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","587");
            props.put("mail.smtp.starttls.enable","true");

            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kanaparthiphani58@gmail.com","feidbsrvpjezzvtc");
                }
            };
            Session session = Session.getInstance(props, authenticator);

            emailUtil.sendEmail(
                    session,
                    sendEmailDto.getTo(),
                    sendEmailDto.getSubject(),
                    sendEmailDto.getBody()
            );

            return "Sent email successfully";
        };
    }
}
