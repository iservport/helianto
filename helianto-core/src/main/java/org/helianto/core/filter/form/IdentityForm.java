package org.helianto.core.filter.form;

import java.util.Collection;

import org.helianto.core.Identity;

/**
 * Identity form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityForm {
	
	/**
	 * Identity principal.
	 */
	public String getPrincipal();
	
	/**
	 * Identity first name.
	 */
	public String getFirstName();
	
	/**
	 * Exclusions.
	 */
	public Collection<Identity> getExclusions();

}
