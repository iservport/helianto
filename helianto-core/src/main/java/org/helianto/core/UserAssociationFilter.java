package org.helianto.core;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * A filter to <code>UserAssociation</code>.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserAssociationFilter extends AbstractUserBackedCriteriaFilter {

	private static final long serialVersionUID = 1L;
	private UserGroup parent;
	private UserGroup child;
	
	/**
	 * Default constructor.
	 */
	public UserAssociationFilter() {
		super();
	}

	/**
	 * User constructor.
	 * 
	 * @param user
	 */
	public UserAssociationFilter(User user) {
		this();
		setUser(user);
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getParent()!=null) {
			appendEqualFilter("parent.id", getParent().getId(), mainCriteriaBuilder);
		}
		if (getChild()!=null) {
			appendEqualFilter("child.id", getChild().getId(), mainCriteriaBuilder);
		}
	}
	
	@Override
	protected String getOrderByString() {
		return "child.userKey";
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("parent.id", getParent().getId(), mainCriteriaBuilder);
		appendEqualFilter("child.id", getChild().getId(), mainCriteriaBuilder);
	}
	
	public String getObjectAlias() {
		return "userassociation";
	}

	public void reset() {
	}
	
	@Override
	public boolean isSelection() {
		if (getParent()!=null && getChild()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Parent Filter.
	 */
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	
	/**
	 * Child Filter.
	 */
	public UserGroup getChild() {
		return child;
	}
	public void setChild(UserGroup child) {
		this.child = child;
	}

}
