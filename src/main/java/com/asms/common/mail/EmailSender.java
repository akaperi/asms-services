package com.asms.common.mail;

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

	public void send(final String toEmail, final String fromEmail, final String subject,final String message, final String type) {
		//final String toEmailId = toEmail;
		//final String fromEmailId = fromEmail;
		//final String mailSubject =

		logger.info("[send] Send the message");
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				mimeMessage.setFrom(new InternetAddress(fromEmail, "devendrasignh77@gmail.com"));
				mimeMessage.setSubject(subject);
				mimeMessage.setContent(message, type);
				

			}
		};

		mailSender.send(preparator);
	}

}
