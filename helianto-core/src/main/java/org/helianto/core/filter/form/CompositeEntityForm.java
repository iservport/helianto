package org.helianto.core.filter.form;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.PublicEntity;

/**
 * Composite entity form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeEntityForm 

	implements 
	  EntityForm
	, PublicEntityForm
	, Cloneable
	
{

	private static final long serialVersionUID = 1L;
	private Operator operator;
	private Entity entity;
	private Class<? extends PublicEntity> clazz;
	private String entityName;
	private String entityAlias;
	private String entityAliasLike;
	private char publicEntityType;
	
	/**
	 * Default constructor.
	 */
	public CompositeEntityForm() {
		setEntityAlias("");
	}
	
	/**
	 * Operator constructor.
	 * 
	 * @param operator
	 */
	public CompositeEntityForm(Operator operator) {
		this();
		setOperator(operator);
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CompositeEntityForm(Entity entity) {
		this();
		setEntity(entity);
	}
	
	public void reset() {
		setPublicEntityType(' ');
		setEntityAliasLike("");
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

	public String getEntityAlias() {
		return entityAlias;
	}
	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
	}

	public String getEntityAliasLike() {
		return entityAliasLike;
	}
	public void setEntityAliasLike(String entityAliasLike) {
		this.entityAliasLike = entityAliasLike;
	}
	
    /**
     * Helper method to clone the form and set a parent.
     * 
     * @param parent
     */
    protected CompositeEntityForm clone(Entity entity) {
    	try {
    		CompositeEntityForm form = (CompositeEntityForm) super.clone();
    		form.setEntity(entity);
    		return form;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException("Unable to clone CompositeEntityForm.");
		}
    }
    

}
