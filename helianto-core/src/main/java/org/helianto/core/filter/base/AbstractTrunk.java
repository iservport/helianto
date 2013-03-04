package org.helianto.core.filter.base;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Base class to implement <code>RootEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@SuppressWarnings("serial")
public abstract class AbstractTrunk 
	implements TrunkEntity {
	
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

