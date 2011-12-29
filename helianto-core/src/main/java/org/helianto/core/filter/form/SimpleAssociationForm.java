package org.helianto.core.filter.form;

import org.helianto.core.UserGroup;

/**
 * A simple <code>AssociationForm</code> implementation.
 * 
 * @author mauriciofernandesdecastro
 */
public class SimpleAssociationForm 

	implements AssociationForm 

{
	
	private static final long serialVersionUID = 1L;
	private UserGroup parent;
	private int childId;
	
	/**
	 * Default constructor.
	 */
	public SimpleAssociationForm() { }
	
	/**
	 * Parent constructor.
	 * 
	 * @param parent
	 */
	public SimpleAssociationForm(UserGroup parent) {
		this();
		setParent(parent);
	}
	
	/**
	 * Parent.
	 */
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}

	public int getParentId() {
		if (getParent()!=null) {
			return getParent().getId();
		}
		return 0;
	}

	public int getChildId() {
		return childId;
	}
	public void setChildId(int childId) {
		this.childId = childId;
	}

}
