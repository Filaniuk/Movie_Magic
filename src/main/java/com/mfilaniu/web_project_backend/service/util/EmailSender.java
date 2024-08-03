package com.mfilaniu.web_project_backend.service.util;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Value
public class EmailSender {

    String fromEmail;

    String fromName;

    String messageTitle;

    String messageBody;

    MultipartFile file;

    // Recipient's email ID needs to be mentioned.
    String toEmail = "mfil55555@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "moviemagicsup@hotmail.com";
    String username = "moviemagicsup@hotmail.com"; // use your own email
    String password = "123456Re";   // use your own email password

    String host = "smtp.office365.com"; // use the SMTP server of your email provider

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public EmailSender(String fromEmail, String fromName,
                       String messageTitle, String messageBody, MultipartFile file) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.messageTitle = messageTitle;
        this.messageBody = messageBody;
        this.file = file;
    }

    public void sendEmail() throws MessagingException {

        Session session = getSession();

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));

        // Set Subject: header field
        message.setSubject(messageTitle);

        final String pattern = """
                ------------------
                #Message sent from email:# %s
                #Message from:# %s
                #Sent date:# %s
                ------------------
                #Title:#
                %s
                ------------------
                #Body:#
                %s
                ------------------
                #Attachment:#
                %s
                ------------------
                """.formatted(fromEmail, fromName, formatter.format(LocalDateTime.now()),
                messageTitle, messageBody, getFileDescription());

        if(file.isEmpty()) {
            message.setText(pattern);
        }
        else {
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText(pattern);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is the attachment
            try {
                addAttachment(multipart, file);
            } catch (IOException e) {
                throw new MessagingException();
            }

            // Send the complete message parts
            message.setContent(multipart);
        }

        Transport.send(message);

    }

    private void addAttachment(Multipart multipart, MultipartFile file) throws MessagingException, IOException {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new ByteArrayDataSource(file.getInputStream(), file.getContentType());
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(file.getOriginalFilename());
        multipart.addBodyPart(messageBodyPart);
    }

    private String getFileDescription() {
        if(file.isEmpty()) {
            return "Files are not attached";
        }
        else {
            return "File name: %s - Size: %s".formatted(file.getOriginalFilename(), file.getSize());
        }
    }

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // or the port number of your SMTP server

        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

}

