package org.helianto.message;

/**
 * Mail sender interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface MessageSender {
	
	/**
	 * Prepare message.
	 * 
	 * @param message
	 */
	void prepareMessage(MessageAdapter<?> message);
	
	/**
	 * Send message.
	 * 
	 * @param message
	 */
	String sendMessage(MessageAdapter<?> message);

}
