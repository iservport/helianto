package org.helianto.process.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.process.ProcessFilter;
import org.springframework.stereotype.Component;

/**
 * Default implementation of <code>ProcessSelectionStrategy</code>
 * interface.
 * 
 * @author Mauricio Fernandes de Castro customer.class = 'C'
 */
@Component("processSelectionStrategy")
public class DefaultProcessSelectionStrategy extends AbstractSelectionStrategy<ProcessFilter> {

	@Override
	protected void preProcessFilter(ProcessFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.append("and process.class=Process ");
	}

	@Override
	protected boolean isSelection(ProcessFilter filter) {
        return (filter.getInternalNumber()>0);
	}

	@Override
	protected void doSelect(ProcessFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("Internal number is: '"+filter.getInternalNumber()+"'");
        }
        mainCriteriaBuilder.appendAnd().appendSegment("internalNumber", "=").append(filter.getInternalNumber());
    	filter.reset();
	}

	@Override
	protected void doFilter(ProcessFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docNameLike", filter.getDocNameLike(), mainCriteriaBuilder);
	}

	private static Log logger = LogFactory.getLog(DefaultProcessSelectionStrategy.class);

}
