package org.helianto.message;

import java.util.Date;
import java.util.Set;

import org.helianto.core.Identity;

/**
 * Message adapter interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MessageAdapter<T> {
	
	/**
	 * Sender.
	 * 
	 * @param from
	 */
	MessageAdapter<T> setFrom(Identity from);
    
	/**
	 * Recipients.
	 * 
	 * @param to
	 */
	MessageAdapter<T> setTo(Set<Identity> to);

	/**
	 * Copied address.
	 * 
	 * @param cc
	 */
	MessageAdapter<T> setCc(Set<Identity> cc);
    
	/**
	 * Reply to address.
	 * 
	 * @param replyTo
	 */
	MessageAdapter<T> setReplyTo(String replyTo);
    
	/**
	 * Subject.
	 * 
	 * @param subject
	 */
	MessageAdapter<T> setSubject(String subject);
    
	/**
	 * Text body.
	 * 
	 * @param text
	 */
	MessageAdapter<T> setText(String text);
    
	/**
	 * Html body.
	 * 
	 * @param html
	 */
	MessageAdapter<T> setHtml(String html);
    
	/**
	 * Sent date.
	 * 
	 * @param sentDate
	 */
	MessageAdapter<T> setSentDate(Date sentDate);
    
	/**
	 * Message.
	 */
    T getMessage();
    
    /**
     * Message setter.
     * 
     * @param message
     */
    void setMessage(Object message);
    
}
