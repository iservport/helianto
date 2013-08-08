package org.helianto.message.sender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.helianto.core.domain.Identity;
import org.helianto.message.AbstractMessageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
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
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			addRecipents(getTo(), RecipientType.TO);
			addRecipents(getCc(), RecipientType.CC);
			addRecipents(getBcc(), RecipientType.BCC);
			helper.setFrom(getFrom().getPrincipal());
			helper.setSubject(getSubject());
			helper.setText(getHtml(), true);
			for (Resource file : getAttachments()) {
				helper.addAttachment(file.getFilename(), file);
			}
			return helper.getMimeMessage();
		} catch (MessagingException e) {
			throw new IllegalArgumentException();
		}
	}

	public String readFile(String filePath) {
		DefaultResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("file:" + filePath);
		String html = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					resource.getFile()));
			String linha = "";
			while ((linha = bufferedReader.readLine()) != null) {
				html = html + linha;
			}
			bufferedReader.close();
			return html;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Iterate to add recipients.
	 * 
	 * @param recipients
	 * @param recipientType
	 */
	public void addRecipents(Set<Identity> recipients, RecipientType recipientType) {
		for (Identity identity: recipients) {
			try {
				if (recipientType.equals(RecipientType.TO)) {
					helper.addTo(identity.getPrincipal());
				}
				else if (recipientType.equals(RecipientType.CC)) {
					helper.addCc(identity.getPrincipal());
				}
				else if (recipientType.equals(RecipientType.BCC)) {
					helper.addBcc(identity.getPrincipal());
				}
				else {
					logger.error("Unable to add recipients.");
				}
			} catch (Exception e) {
				logger.error("Unable to add recipients.");
			}
		}
	}

	private JavaMailSender javaMailSender;
	private MimeMessageHelper helper;

	@javax.annotation.Resource
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	private static final Logger logger = LoggerFactory.getLogger(JavaMailMessageAdapter.class);

}
