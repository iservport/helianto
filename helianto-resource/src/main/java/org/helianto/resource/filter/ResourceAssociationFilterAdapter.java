package org.helianto.resource.filter;


import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.resource.ResourceAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource association filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
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
	public void preProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		if (getFilter().getParent()!=null) {
			appendEqualFilter("parent.id", getFilter().getParent().getId(), mainCriteriaBuilder);
			setOrderByString("child.resourceCode");
			logger.debug("Filter parent constraint set to {}.", getFilter().getParent());
		}
		else if (getFilter().getChild()!=null) {
			appendEqualFilter("child.id", getFilter().getChild().getId(), mainCriteriaBuilder);
			setOrderByString("parent.resourceCode");
			logger.debug("Filter child constraint set to {}.", getFilter().getChild());
		}
	}

	public void reset() { }
	
	@Override
	public boolean isSelection() {
		return getFilter().getParent()!=null && getFilter().getChild()!=null;
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("child.id", getFilter().getChild().getId(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) { }
	
	private static Logger logger = LoggerFactory.getLogger(ResourceAssociationFilterAdapter.class);

}
