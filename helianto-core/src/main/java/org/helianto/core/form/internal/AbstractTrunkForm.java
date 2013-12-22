package org.helianto.core.form.internal;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Base class to trunk (aggregated to Entity) forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractTrunkForm 
	extends AbstractSearchForm implements TrunkEntity {

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private int entityId;
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public int getEntityId() {
		if (getEntity()!=null) {
			return getEntity().getId();
		}
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	
}
