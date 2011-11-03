package org.helianto.core.filter.form;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.PublicEntity;

/**
 * Base class to PublicEntityForm interface.
 * 
 * @author mauriciofernandesdecastro
 */
public abstract class AbstractPublicEntityForm implements PublicEntityForm {
	
	private static final long serialVersionUID = 1L;
	private Operator operator;
	private Entity entity;
	private Class<? extends PublicEntity> clazz;
	private String entityName;
	private char publicEntityType;
	
	/**
	 * Default constructor.
	 */
	public AbstractPublicEntityForm() {
		
	}
	
	public void reset() {
		setPublicEntityType(' ');
	}
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Class<? extends PublicEntity> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends PublicEntity> clazz) {
		this.clazz = clazz;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public char getPublicEntityType() {
		return publicEntityType;
	}
	public void setPublicEntityType(char publicEntityType) {
		this.publicEntityType = publicEntityType;
	}

}