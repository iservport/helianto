package org.helianto.message.sender;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.helianto.message.MessageAdapter;
import org.helianto.message.MessageSender;
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
		javaMailSender.send((MimeMessage) message.getMessage());
		return "";
	}
	
    private JavaMailSender javaMailSender;
    
    @Resource(name="javaMailSender")
    public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

}
