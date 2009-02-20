package org.helianto.process.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.orm.DefaultCategorySelectionStrategy;
import org.helianto.process.InheritanceType;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.springframework.stereotype.Component;

/**
 * Default implementation for <code>SelectionStrategy</code> interface
 * using <code>ProcessDocument</code> parameter.
 * 
 * @author Mauricio Fernandes de Castro customer.class = 'C'
 */
@Component("processDocumentSelectionStrategy")
public class DefaultProcessDocumentSelectionStrategy extends AbstractSelectionStrategy<ProcessDocumentFilter> {

	@Override
	protected void preProcessFilter(ProcessDocumentFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (!filter.getClazz().equals(ProcessDocument.class)) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Document class is: '"+filter.getClazz()+"'");
	        }
			mainCriteriaBuilder.appendAnd().append(filter.getClazz());
		}
		if (filter.getPartner()!=null) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Restricted to final processes from: '"+filter.getPartner()+"'");
	        }
	        CriteriaBuilder partnerCriteria = new CriteriaBuilder(mainCriteriaBuilder.getPrefix());
	        partnerCriteria.appendSegment("partner.id", "=")
				.append(filter.getPartner().getId());
	        partnerCriteria.appendOr().appendSegment("inheritanceType", "=")
	        	.append(InheritanceType.CONCRETE.getValue());
			mainCriteriaBuilder.appendAnd().append(partnerCriteria);
		}
	}

	@Override
	protected boolean isSelection(ProcessDocumentFilter filter) {
        return (filter.getDocCode().length()>0);
	}

	@Override
	protected void doSelect(ProcessDocumentFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("Document code is: '"+filter.getDocCode()+"'");
        }
        mainCriteriaBuilder.appendAnd().appendSegment("docCode", "=").appendString(filter.getDocCode());
    	filter.reset();
	}

	@Override
	protected void doFilter(ProcessDocumentFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docName", filter.getDocNameLike(), mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", filter.getInheritanceType(), mainCriteriaBuilder);
	}

	private static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);

}
