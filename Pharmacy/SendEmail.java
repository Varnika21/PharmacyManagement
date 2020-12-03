package Pharmacy;
import java.io.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class SendEmail {
	 String path="";
	 String email="";
	 String usrname="";
    public SendEmail(String usrname,String path, String email) throws Exception {
		// TODO Auto-generated constructor stub
    	this.path=path;
    	this.email=email;
    	this.usrname=usrname;
	}

	void send_mail()
	{
        // authentication info
        final String username = "pharmacyvce";
        final String password = "java2020";
        String fromEmail = "pharmacyvce@gmail.com";
        String toEmail = email;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Hello "+usrname);
            Multipart emailContent = new MimeMultipart();
            // Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Your Receipt at our store");
            // Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile(path);
            // Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);
            // Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}