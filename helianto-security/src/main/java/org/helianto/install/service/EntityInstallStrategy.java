package org.helianto.install.service;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;

import java.util.List;

/**
 * Interface to define how entities must be installed.
 * 
 * @author mauriciofernandesdecastro
 */
public interface EntityInstallStrategy {
	
	/**
	 * Generate a list of entity prototypes.
	 * 
	 * @param params
	 */
	List<Entity> generateEntityPrototypes(Object... params);
	
	/**
	 * Assure the prototyped entity is persistent.
	 * 
	 * @param contextName
	 * @param prototype
	 */
	Entity installEntity(String contextName, Entity prototype);
	
	void createEntities(String contextName, List<Entity> prototypes, Identity identity);
	
	/**
	 * Remove lead.
	 * 
	 * @param leadPrincipal
	 */
	String removeLead(String leadPrincipal);

}
