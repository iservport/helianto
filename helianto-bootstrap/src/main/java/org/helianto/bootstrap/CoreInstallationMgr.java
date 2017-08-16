package org.helianto.bootstrap;

import org.helianto.classic.def.CategoryGroup;
import org.helianto.core.domain.Category;
import org.helianto.core.domain.Credential;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Identity;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.Unit;

/**
 * Core installation tasks.
 * 
 * @author mauriciofernandesdecastro
 */
public interface CoreInstallationMgr {

	/**
	 * Install category.
	 * 
     * @param entity
     * @param categoryGroup
     * @param categoryCode
	 */
	public Category installCategory(Entity entity, CategoryGroup categoryGroup, String categoryCode, String categoryName);

    /**
     * <p>Create <code>Credential</code> and <code>Identity</code>.</p>
     * 
     * @param principal
     */
    public Credential installIdentity(String principal);
    
    /**
     * <p>Create <code>Credential</code>.</p>
     * 
     * @param identity
     */
    public Credential installCredential(Identity identity);
    
	/**
     * Install <code>PublicEntity</code>.
     * 
     * @param entity
     */
	public PublicEntity installPublicEntity(Entity entity);
	
	/**
	 * Install a unit if does not exist.
	 * 
	 * @param category
	 * @param unitCode
	 * @param unitName
	 */
	public Unit installUnit(Category category, String unitCode, String unitName);
	
}
