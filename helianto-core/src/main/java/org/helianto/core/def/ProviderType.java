package org.helianto.core.def;

/**
 * Authorization provider types.
 * 
 * @author mauriciofernandesdecastro
 */
public enum ProviderType {
	
	username,
	email(true),
	gmail(true),
	twitter,
	linkedin,
	facebook;
	
	private ProviderType() {
		this(false);
	}
	
	private ProviderType(boolean acceptEmail) {
		this.acceptEmail = acceptEmail;
	}
	
	private boolean acceptEmail;
	
	public boolean isAcceptEmail() {
		return this.acceptEmail;
	}

}
