package org.helianto.core.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.CriteriaBuilder;

import org.helianto.core.UnitFilter;
import org.helianto.core.dao.UnitSelectionStrategy;

/**
 * Default implementation of <code>UnitSelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DefaultUnitSelectionStrategy implements UnitSelectionStrategy {

	public String createCriteriaAsString(UnitFilter filter, String prefix) {
        // main filter handling
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        mainCriteriaBuilder.appendEntityFromUserBackedFilter(filter);
        
        if (filter.getUnitCode().length()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("UnitCode is: '"+filter.getUnitCode()+"'");
            }
        	appendUnitCodeFilter(filter, mainCriteriaBuilder);
        	filter.reset();
        }
        // demais casos
        else {
            appendCategoryFilter(filter, mainCriteriaBuilder);
        	appendUnitNameLikeFilter(filter, mainCriteriaBuilder);
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
    /**
     * <code>unitCode</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendUnitCodeFilter(UnitFilter filter, CriteriaBuilder criteriaBuilder) {
        criteriaBuilder.appendAnd().appendSegment("unitCode", "=")
        .appendString(filter.getUnitCode());
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
    
    /**
     * <code>unitNameLike</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendUnitNameLikeFilter(UnitFilter filter, CriteriaBuilder criteriaBuilder) {
    	if (filter.getUnitNameLike()!="") {
            criteriaBuilder.appendAnd().appendSegment("unitNameLike", "like")
            .appendLike(filter.getUnitNameLike());
        }
    }
    
    
    private static Log logger = LogFactory.getLog(DefaultUnitSelectionStrategy.class);

}
