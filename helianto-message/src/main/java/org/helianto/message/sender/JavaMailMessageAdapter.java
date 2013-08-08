package org.helianto.message.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.helianto.message.AbstractMessageAdapter;
import org.springframework.core.io.Resource;
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
			MimeMessageHelper helper = new MimeMessageHelper(super.getMessage(), true);
			helper.setTo(getToAsStringArray());
			helper.setFrom(getFrom().getPrincipal());
			helper.setSubject(getSubject());
			helper.setText(getHtml(), true);
			for (Resource file: getAttachments()) {
				helper.addAttachment(file.getFilename(), file);
			}
			return getMessage();
		} catch (MessagingException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
