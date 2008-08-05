package org.helianto.process.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.orm.DefaultCategorySelectionStrategy;
import org.helianto.process.ProcessFilter;
import org.helianto.process.dao.ProcessSelectionStrategy;
import org.springframework.stereotype.Component;

/**
 * Default implementation of <code>ProcessSelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("processSelectionStrategy")
public class DefaultProcessSelectionStrategy implements ProcessSelectionStrategy {

	public String createCriteriaAsString(ProcessFilter filter, String prefix) {
        // main filter handling
        CriteriaBuilder mainCriteriaBuilder = new CriteriaBuilder(prefix);
        mainCriteriaBuilder.appendEntityFromUserBackedFilter(filter);
        
        
        if (filter.getInternalNumber()>0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Internal number is: '"+filter.getInternalNumber()+"'");
            }
        	appendInternalNumberFilter(filter, mainCriteriaBuilder);
        	filter.reset();
        }
        else {
        	appendDocNameLikeFilter(filter, mainCriteriaBuilder);
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("Filter query: "+mainCriteriaBuilder.getCriteriaAsString());
        }
        return mainCriteriaBuilder.getCriteriaAsString();
    }
	
    /**
     * <code>internalNumber</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendInternalNumberFilter(ProcessFilter filter, CriteriaBuilder criteriaBuilder) {
        criteriaBuilder.appendAnd().appendSegment("internalNumber", "=")
        .append(filter.getInternalNumber());
    }
    
    /**
     * <code>docNameLike</code> filter segment
     * @param filter
     * @param criteriaBuilder
     */
    protected void appendDocNameLikeFilter(ProcessFilter filter, CriteriaBuilder criteriaBuilder) {
    	if (filter.getDocNameLike().length()>0) {
            criteriaBuilder.appendAnd().appendSegment("docNameLike", "like")
            .appendLike(filter.getDocNameLike());
    	}
    }
    
    private static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);

}
