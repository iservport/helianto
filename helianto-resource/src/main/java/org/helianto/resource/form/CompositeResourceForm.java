package org.helianto.resource.form;

import org.helianto.core.Entity;
import org.helianto.document.base.AbstractPrivateControl;
import org.helianto.resource.domain.ResourceAssociation;
import org.helianto.resource.domain.ResourceGroup;

/**
 * Composite resource form.
 * 
 * @author mauriciofernandesdecastro
 */
public class CompositeResourceForm extends AbstractPrivateControl implements ResourceGroupForm {

	private static final long serialVersionUID = 1L;
	private char type;
	private ResourceGroup parent;
	private ResourceGroup child;
	private String resourceCode;
	private String resourceName;
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
	 * @param parent
	 */
	public CompositeResourceForm(ResourceGroup parent) {
		this(parent.getEntity());
		setParent(parent);
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
	
	public ResourceGroup getParent() {
		return parent;
	}
	public void setParent(ResourceGroup parent) {
		this.parent = parent;
	}
	public long getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
	}
	
	public String getParentName() {
		return "parentAssociation.parent";
	}
	
	public ResourceGroup getChild() {
		return child;
	}
	public void setChild(ResourceGroup child) {
		this.child = child;
	}
	public long getChildId() {
		if (getChild()!=null) {
			return getChild().getId();
		}
		return 0;
	}
	
	public String getChildName() {
		return "childAssociation.child";
	}
	

	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public char getResourceType() {
		return resourceType;
	}
	public void setResourceType(char resourceType) {
		this.resourceType = resourceType;
	}

}
