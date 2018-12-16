package com.bigeater.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

/**
 * @author dalao
 */
public class MailUtil {
    public static MailBuilder builder() {
        return new MailBuilder();
    }

    public static class MailBuilder {
        private String mailHost = "smtp.exmail.qq.com";
        private String mailProtocol = "smtp";
        private String userName = null;
        private String passWord = null;
        private String recipients = null;
        private String subject = "";
        private String content = "";
        private String attachmentName = null;
        private DataSource dataSource = null;

        public MailBuilder recipients(String recipients) {
            this.recipients = recipients;
            return this;
        }

        public MailBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MailBuilder attachment(String attachmentName, File attachment) {
            this.attachmentName = attachmentName;
            this.dataSource = new FileDataSource(attachment);
            return this;
        }

        public MailBuilder attachment(String attachmentName, InputStream inputStream, String mimeType) throws IOException {
            this.attachmentName = attachmentName;
            this.dataSource = new ByteArrayDataSource(inputStream, mimeType);
            return this;
        }

        public MailBuilder mailHost(String mailHost) {
            this.mailHost = mailHost;
            return this;
        }

        public MailBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public MailBuilder passWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public void sendMail() throws MessagingException, UnsupportedEncodingException {
            assert mailHost != null && mailProtocol != null && userName != null && passWord != null &&
                    recipients != null && subject != null && content != null;
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", mailHost);
            properties.put("mail.transport.protocol", mailProtocol);
            properties.setProperty("mail.sender.username", userName);
            properties.setProperty("mail.sender.password", passWord);
            properties.setProperty("mail.smtp.auth", "true");
            //每次一个新session，防止切换使用不同的发件方用同一个session报501错误的问题
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, passWord);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName)); // 发件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients, false)); // 收件人
            message.setSubject(subject); // 主题
            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart); // 正文
            if (dataSource != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachmentName));
                multipart.addBodyPart(attachmentBodyPart); // 附件
            }
            message.setContent(multipart);
            message.saveChanges();
            Transport.send(message);
        }
    }
}
