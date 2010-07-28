package org.helianto.core;

import org.helianto.core.filter.AbstractCompositeListFilter;
import org.helianto.core.filter.CriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A composite filter to <code>UserAssociation</code>.
 * 
 * <p>
 * If a parent filter is set, the current item of the parent list
 * may be injected in the field 'parent' to constrain the child list.
 * </p>
 * 
 * @author mauriciofernandesdecastro
 */
public class UserAssociationFilter extends AbstractCompositeListFilter {

	private static final long serialVersionUID = 1L;
	private boolean syncParent = false;
	private UserGroup parent;
	private UserGroup child;
	private String parentKey;
	private Identity childIdentity;
	
	/**
	 * Default constructor.
	 */
	public UserAssociationFilter() {
		super();
	}

	/**
	 * Composite constructor.
	 * 
	 * @param userFilter
	 */
	public UserAssociationFilter(UserFilter userFilter) {
		this();
		setParentFilter(userFilter);
	}
	
	@Override
	protected void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.preProcessFilter(mainCriteriaBuilder);
		if (isSyncParent() && getParentItem()!=null) {
			setParent((UserGroup) getParentItem());
			logger.debug("User association list is in sync with user group parent list.");
		}
	}

	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getParent()!=null) {
			appendEqualFilter("parent.id", getParent().getId(), mainCriteriaBuilder);
		}
		if (getChildIdentity()!=null) {
			appendEqualFilter("child.identity.id", getChildIdentity().getId(), mainCriteriaBuilder);
		}
		if (getChild()!=null) {
			appendEqualFilter("child.id", getChild().getId(), mainCriteriaBuilder);
		}
		appendEqualFilter("parent.userKey", getParentKey(), mainCriteriaBuilder);
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
	 * True forces pre-processing to update parent filter with parent list 
	 * current selection.
	 */
	public boolean isSyncParent() {
		return syncParent;
	}
	public void setSyncParent(boolean syncParent) {
		this.syncParent = syncParent;
	}
	
	/**
	 * Parent filter.
	 */
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	
	/**
	 * Child filter.
	 */
	public UserGroup getChild() {
		return child;
	}
	public void setChild(UserGroup child) {
		this.child = child;
	}
	
	/**
	 * Parent key filter.
	 */
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	
	/**
	 * Child identity filter.
	 */
	public Identity getChildIdentity() {
		return childIdentity;
	}
	public void setChildIdentity(Identity childIdentity) {
		this.childIdentity = childIdentity;
	}
	
	private static Logger logger = LoggerFactory.getLogger(UserAssociationFilter.class);

}
