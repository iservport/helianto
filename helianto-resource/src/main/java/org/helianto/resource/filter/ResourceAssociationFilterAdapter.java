package org.helianto.resource.filter;


import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.resource.domain.classic.ResourceAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource association filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated
 * @see ResourceAssociationFormFilterAdapter
 */
public class ResourceAssociationFilterAdapter extends AbstractFilterAdapter<ResourceAssociation> {

	private static final long serialVersionUID = 1L;

	/**
	 * Filter constructor.
	 * 
	 * @param filter
	 */
	public ResourceAssociationFilterAdapter(ResourceAssociation filter) {
		super(filter);
	}
	
	/**
	 * Restrict selection to a given entity, if any. 
	 */
	@Override
	public boolean preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		boolean connect = false;
		if (getForm().getParent()!=null) {
			appendEqualFilter("parent.id", getForm().getParent().getId(), mainCriteriaBuilder);
			setOrderByString("child.resourceCode");
			logger.debug("Filter parent constraint set to {}.", getForm().getParent());
			connect = true;
		}
		else if (getForm().getChild()!=null) {
			appendEqualFilter("child.id", getForm().getChild().getId(), mainCriteriaBuilder);
			setOrderByString("parent.resourceCode");
			logger.debug("Filter child constraint set to {}.", getForm().getChild());
			connect = true;
		}
		return connect;
	}

	public void reset() { }
	
	@Override
	public boolean isSelection() {
		return getForm().getParent()!=null && getForm().getChild()!=null;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("child.id", getForm().getChild().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
	
	private static Logger logger = LoggerFactory.getLogger(ResourceAssociationFilterAdapter.class);

}
