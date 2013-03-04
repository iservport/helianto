package org.helianto.core.form;

import java.util.Collection;

import org.helianto.core.domain.Identity;

/**
 * Identity form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityForm 
	extends SearchForm {
	
	/**
	 * Identity principal.
	 */
	public String getPrincipal();
	
	/**
	 * Identity first name.
	 */
	public String getFirstName();
	
	/**
	 * Identity last name.
	 */
	public String getLastName();
	
	/**
	 * Gender.
	 */
	public char getGender();
	
	/**
	 * Identity type.
	 */
	public char getIdentityType();
	
	/**
	 * Notification.
	 */
	public char getNotification();
	
	/**
	 * Exclusions.
	 */
	public Collection<Identity> getExclusions();

}
