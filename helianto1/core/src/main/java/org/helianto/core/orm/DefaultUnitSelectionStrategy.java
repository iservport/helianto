package org.helianto.core.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.UnitFilter;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>SelectionStrategy</code>
 * interface using <code>UnitFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("unitSelectionStrategy")
public class DefaultUnitSelectionStrategy extends AbstractSelectionStrategy<UnitFilter> {

	@Override
	protected boolean isSelection(UnitFilter filter) {
		return (filter.getUnitCode().length()>0);
	}

	@Override
	protected void doSelect(UnitFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("UnitCode is: '"+filter.getUnitCode()+"'");
        }
    	appendEqualFilter("unitCode", filter.getUnitCode(), mainCriteriaBuilder);
    	filter.reset();
	}

	@Override
	protected void doFilter(UnitFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        appendCategoryFilter(filter, mainCriteriaBuilder);
    	appendLikeFilter("unitNameLike", filter.getUnitNameLike(), mainCriteriaBuilder);
	}

    /**
     * <code>category</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendCategoryFilter(UnitFilter filter, CriteriaBuilder criteriaBuilder) {
    	if (filter.getCategory()!=null) {
	        criteriaBuilder.appendAnd().appendSegment("category.id", "=")
	        .append(filter.getCategory().getId());
    	}
    }
    
    private static Log logger = LogFactory.getLog(DefaultUnitSelectionStrategy.class);

}
