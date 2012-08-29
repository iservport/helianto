package org.helianto.message.def;

/**
 * Define notification types.
 * 
 * @author mauriciofernandesdecastro
 */
public enum NotificationAddressType {
	
	/**
	 * Notification by email.
	 */
	EMAIL('E');
	
	private NotificationAddressType(char value) {
		this.value = value;
	}
	
	private char value;
	
	/**
	 * Value assigned to the notification type.
	 */
	public char getValue() {
		return value;
	}
}
