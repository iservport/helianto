package org.helianto.message.sender;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.helianto.core.MessageAdapter;
import org.helianto.core.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Wraps a JavaMailSender to accept <code>MessageAdapter</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public class SmtpMailSender implements MessageSender {

	public void prepareMessage(MessageAdapter<?> message) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		message.setMessage(mimeMessage);
	}

	public String sendMessage(MessageAdapter<?> message) {
		MimeMessage mimeMessage = (MimeMessage) message.getMessage();
		try {
			javaMailSender.send(mimeMessage);
			logger.debug("E-mail sent from {}.", mimeMessage.getFrom().toString());
		} catch (Exception e) {
			logger.error("Unable to send e-mail!");
		}
		return "";
	}

	private JavaMailSender javaMailSender;

	@Resource(name = "javaMailSender")
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SmtpMailSender.class);

}
