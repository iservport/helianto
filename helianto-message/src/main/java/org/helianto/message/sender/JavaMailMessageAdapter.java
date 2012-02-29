package org.helianto.message.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.helianto.message.AbstractMessageAdapter;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Java mail message (not thread safe).
 * 
 * @author mauriciofernandesdecastro
 */
public class JavaMailMessageAdapter extends AbstractMessageAdapter<MimeMessage> {
	
	/**
	 * Java mail message constructor.
	 * 
	 * @param mimeMessage
	 */
	public JavaMailMessageAdapter() {
		super();
	}
	
	/**
	 * Adapter method.
	 */
	protected String[] getToAsStringArray() {
		String[] toArray = new String[getTo().size()];
		return toArray;
	}
	
	public MimeMessage getMessage() {
		try {
			MimeMessageHelper helper = new MimeMessageHelper(getMessage(), true);
			helper.setTo(getToAsStringArray());
			helper.setFrom(getFrom().getPrincipal());
			helper.setSubject(getSubject());
			helper.setText(getHtml(), true);
			return getMessage();
		} catch (MessagingException e) {
			throw new IllegalArgumentException();
		}
	}
	
}