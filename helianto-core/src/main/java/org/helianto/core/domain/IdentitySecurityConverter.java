package org.helianto.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Helper class to convert old plain text password to new encoded consumerSecret fields.
 * 
 * @author mauriciofernandesdecastro
 */
public class IdentitySecurityConverter {
	
	/**
	 * Constructor.
	 * 
	 * @param passwordEncoder
	 */
	@Autowired
	public IdentitySecurityConverter(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * Convert field password to consumerSecret.
	 *  
	 * @param identitySecurity
	 */
	public void convert(IdentitySecurity identitySecurity) {
		if (identitySecurity!=null) {
			String consumerSecret = identitySecurity.getConsumerSecret();
			if (consumerSecret==null | (consumerSecret!=null && consumerSecret.isEmpty())) {
				identitySecurity.setConsumerSecret(passwordEncoder.encode(identitySecurity.getPassword()));
//				identitySecurity.setPassword("");
			}
		}
	}
	
	private PasswordEncoder passwordEncoder;

}
