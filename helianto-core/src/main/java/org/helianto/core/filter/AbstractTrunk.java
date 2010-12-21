package org.helianto.core.filter;

import org.helianto.core.Entity;
import org.helianto.core.TrunkEntity;

/**
 * Base class to implement <code>RootEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractTrunk implements TrunkEntity {
	
	private Entity entity;
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	protected AbstractTrunk(Entity entity) {
		setEntity(entity);
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}

