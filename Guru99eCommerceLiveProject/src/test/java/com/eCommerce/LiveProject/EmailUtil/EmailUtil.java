package com.eCommerce.LiveProject.EmailUtil;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_AUTH_USER = "userid";
	private static final String SMTP_AUTH_PWD = "pass";

	private static final String emailMsgText = "Orders List in CV Formatted File";
	private static final String emailSubjectText = "Orders List in CSV Formeatted File";
	private static final String emailFromAddress = "userid@gmail.com";

	private static final String[] emailList = { "supportw11@axyz.com", "BMX97M@tpg.com" };
	private static String sFilename = null;

	public static void emailUtil(String vFilename) throws Throwable {
		sFilename = vFilename;
		EmailUtil stmMailSender = new EmailUtil();
		stmMailSender.postEmail(emailList, emailSubjectText, emailMsgText, emailFromAddress);

	}

	private BodyPart messgeBodyPart;

	public void postEmail(String recipients[], String subject, String message, String from) throws Throwable {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", SMTP_HOST_NAME);
		prop.put("mail.smtp.auth", "true");

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(prop, auth);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		Multipart multipart = new MimeMultipart();
		messgeBodyPart = new MimeBodyPart();
		String filename = sFilename;
		DataSource source = new FileDataSource(filename);
		messgeBodyPart.setDataHandler(new DataHandler(source));
		multipart.addBodyPart(messgeBodyPart);

		msg.setContent(multipart);
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		msg.setContent(multipart);
		Transport.send(msg);
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}

	}

}
