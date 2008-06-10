package org.helianto.core.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.CriteriaBuilder;

import org.helianto.core.CategoryFilter;
import org.helianto.core.dao.CategorySelectionStrategy;

/**
 * Default implementation of <code>CategorySelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultCategorySelectionStrategy implements CategorySelectionStrategy {

	public String createCriteriaAsString(CategoryFilter filter, String prefix) {
        // main filter handling
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        mainCriteriaBuilder.appendEntityFromUserBackedFilter(filter);
        
        // force to filter by group
        appendCategoryGroupFilter(filter, mainCriteriaBuilder);
        
        if (filter.getCategoryCode().length()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("CategoryCode is: '"+filter.getCategoryCode()+"'");
            }
        	appendCategoryCodeFilter(filter, mainCriteriaBuilder);
        	filter.reset();
        }
        else {
        	appendCategoryNameLikeFilter(filter, mainCriteriaBuilder);
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
    /**
     * <code>categoryGroup</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendCategoryGroupFilter(CategoryFilter filter, CriteriaBuilder criteriaBuilder) {
        criteriaBuilder.appendAnd().appendSegment("categoryGroup", "=")
        .append(filter.getCategoryGroup().getValue());
    }
    
    /**
     * <code>categoryCode</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendCategoryCodeFilter(CategoryFilter filter, CriteriaBuilder criteriaBuilder) {
        criteriaBuilder.appendAnd().appendSegment("categoryCode", "=")
        .appendString(filter.getCategoryCode());
    }
    
    /**
     * <code>categoryNameLike</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendCategoryNameLikeFilter(CategoryFilter filter, CriteriaBuilder criteriaBuilder) {
    	if (filter.getCategoryNameLike()!="") {
            criteriaBuilder.appendAnd().appendSegment("categoryNameLike", "like")
            .appendLike(filter.getCategoryNameLike());
        }
    }
    
    
    private static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);

}
