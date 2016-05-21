package org.helianto.core.install;

import org.helianto.core.domain.Identity;

/**
 * Indentity installer.
 * 
 * @author mauriciofernandesdecastro
 */
public interface IdentityInstaller {

	Identity installIdentity(String principal);
	
	Identity installIdentity(String principal, String displayName, String firstName, String lastName);
	
}
