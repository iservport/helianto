package org.helianto.core.form;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;

/**
 * Base class to trunk (aggregated to Entity) forms.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractTrunkForm 

	extends AbstractSearchForm
	
	implements TrunkEntity 

{

	private static final long serialVersionUID = 1L;
	private Entity entity;
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
