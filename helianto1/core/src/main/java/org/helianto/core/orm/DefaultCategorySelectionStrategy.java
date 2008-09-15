package org.helianto.core.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.CategoryFilter;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>SelectionStrategy</code>
 * interface using <code>CategoryFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("categorySelectionStrategy")
public class DefaultCategorySelectionStrategy extends AbstractSelectionStrategy<CategoryFilter> {

	@Override
	protected void preProcessFilter(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        // force to filter by group
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryGroup is: '"+filter.getCategoryGroup()+"'");
        }
        mainCriteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(filter.getCategoryGroup().getValue());
	}

	@Override
	protected boolean isSelection(CategoryFilter filter) {
		return (filter.getCategoryCode().length()>0);
	}

	@Override
	protected void doSelect(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("CategoryCode is: '"+filter.getCategoryCode()+"'");
        }
    	appendEqualFilter("categoryCode", filter.getCategoryCode(), mainCriteriaBuilder);
    	filter.reset();
	}

	@Override
	protected void doFilter(CategoryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("categoryNameLike", filter.getCategoryNameLike(), mainCriteriaBuilder);
	}

    private static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);


}
