package org.helianto.core.domain;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Helper class to convert old plain text password to new encoded consumerSecret fields.
 * 
 * @author mauriciofernandesdecastro
 */
public class ConnectionDataConverter {
	
	/**
	 * Constructor.
	 * 
	 * @param passwordEncoder
	 */
	@Autowired
	public ConnectionDataConverter(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * Convert field password to consumerSecret.
	 *  
	 * @param connectionData
	 */
	public void convert(ConnectionData connectionData) {
		if (connectionData!=null) {
			String consumerSecret = connectionData.getConsumerSecret();
			if (consumerSecret==null | (consumerSecret!=null && consumerSecret.isEmpty())) {
				connectionData.setConsumerSecret(passwordEncoder.encode(connectionData.getPassword()));
//				connectionData.setPassword("");
			}
		}
	}
	
	/**
	 * Convert field password to consumerSecret.
	 *  
	 * @param connectionDataCollection
	 */
	public void convertAll(Collection<ConnectionData> connectionDataCollection) {
		for (ConnectionData connectionData: connectionDataCollection) {
			convert(connectionData);
		}
	}
	
	private PasswordEncoder passwordEncoder;

}
