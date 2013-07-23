package org.helianto.core.form;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.helianto.core.domain.Province;
import org.helianto.core.form.internal.AbstractAddressForm;

/**
 * Composite entity form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeEntityForm 
	extends AbstractAddressForm
	implements 
	  EntityForm
	, PublicEntityForm
	, PublicAddressForm
	, KeyTypeForm
	, Cloneable {

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private char type;
	private String entityName = "";
	private String entityAlias = "";
	private String entityAliasLike = "";
	private char publicEntityType;
	private Province province;
	private String provinceCode = "";
	private String postalCode = "";
	private String keyCode = "";
	private String keyName = "";
	private char activityState;
	private char nature;
	
	/**
	 * Default constructor.
	 */
	public CompositeEntityForm() {
		this("");
	}
	
	/**
	 * Entity constructor.
	 * 
	 * @param alias
	 */
	public CompositeEntityForm(String alias) {
		setEntityAlias(alias);
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
	
	/**
	 * Key code constructor.
	 * 
	 * @param operator
	 * @param keyCode
	 */
	public CompositeEntityForm(Operator operator, String keyCode) {
		this(operator);
		setKeyCode(keyCode);
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
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
	
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	public char getActivityState() {
		return activityState;
	}
	public void setActivityState(char activityState) {
		this.activityState = activityState;
	}
	
	public char getNature() {
		return nature;
	}
	public void setNature(char nature) {
		this.nature = nature;
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
