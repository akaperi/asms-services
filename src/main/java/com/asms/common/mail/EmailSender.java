package com.asms.common.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;


@Component
public class EmailSender {
	private static Logger logger = LoggerFactory.getLogger(EmailSender.class);

	@Autowired
	private JavaMailSender mailSender;

	public void send(final String schoolName,final String toEmail, final String fromEmail, final String subject,final String message, final String type,final Date date) {
		//final String toEmailId = toEmail;
		//final String fromEmailId = fromEmail;
		//final String mailSubject =
final String msg=schoolName+"\n\n\n\n"+"Sent on: "+new Date().toString()+"\n\n\n\n"+message;
		logger.info("[send] Send the message");
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setText(schoolName);
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				mimeMessage.setFrom(new InternetAddress(fromEmail, "devendrasignh77@gmail.com"));
				mimeMessage.setSubject(subject);
				mimeMessage.setContent(schoolName, type);
				mimeMessage.setSentDate(date);
				mimeMessage.setContent(msg, type);
				
				

			}
		};

		mailSender.send(preparator);
	}

}
