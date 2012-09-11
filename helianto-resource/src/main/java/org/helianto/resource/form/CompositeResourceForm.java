package org.helianto.resource.form;

import org.helianto.core.Entity;
import org.helianto.core.filter.form.AbstractControllable;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Composite resource form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeResourceForm 

	extends AbstractControllable 
	
	implements 
	  ResourceGroupForm
	, Cloneable

{

	private static final long serialVersionUID = 1L;
	private char type;
	private ResourceGroup resourceGroup;
	private String resourceCode;
	private char resourceType;
	
	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public CompositeResourceForm(Entity entity) {
		super();
		setEntity(entity);
	}
	
	/**
	 * Parent constructor.
	 * 
	 * @param resourceGroup
	 */
	public CompositeResourceForm(ResourceGroup resourceGroup) {
		this(resourceGroup.getEntity());
		setResourceGroup(resourceGroup);
	}
	
	@Override
	public void reset() {
		super.reset();
		setResourceType(' ');
	}

	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public ResourceGroup getResourceGroup() {
		return resourceGroup;
	}
	public void setResourceGroup(ResourceGroup parent) {
		this.resourceGroup = parent;
	}
	
	public int getResourceGroupId() {
		if (getResourceGroup()!=null) {
			return getResourceGroup().getId();
		}
		return 0;
	}
	
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	public char getResourceType() {
		return resourceType;
	}
	public void setResourceType(char resourceType) {
		this.resourceType = resourceType;
	}
	
	/**
	 * Create a clone and set its parent.
	 * 
	 * @param parent
	 */
	public CompositeResourceForm clone(ResourceGroup parent) {
		try {
			CompositeResourceForm form = (CompositeResourceForm) clone();
			form.setResourceGroup(parent);
			return form;
		} catch (CloneNotSupportedException e) {
			throw new UnsupportedOperationException("Unable to create clone.");
		}
	}

	/**
	 * Create a clone and set its parent.
	 * 
	 * @param parent
	 * @param resourceType
	 */
	public CompositeResourceForm clone(ResourceGroup parent, char resourceType) {
		CompositeResourceForm form = (CompositeResourceForm) clone(parent);
		form.setResourceType(resourceType);
		return form;
	}

}
