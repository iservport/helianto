package org.helianto.partner.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.partner.PartnerRegistryFilter;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>SelectionStrategy</code>
 * interface using <code>CategoryFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerRegistrySelectionStrategy")
public class DefaultPartnerRegistrySelectionStrategy extends AbstractSelectionStrategy<PartnerRegistryFilter> {

	@Override
	protected boolean isSelection(PartnerRegistryFilter filter) {
		return (filter.getPartnerAlias().length()>0);
	}

	@Override
	protected void doSelect(PartnerRegistryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
        if (logger.isDebugEnabled()) {
            logger.debug("PartnerAlias is: '"+filter.getPartnerAlias()+"'");
        }
    	appendEqualFilter("partnerAlias", filter.getPartnerAlias(), mainCriteriaBuilder);
    	filter.reset();
	}

	@Override
	protected void doFilter(PartnerRegistryFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("partnerNameLike", filter.getPartnerNameLike(), mainCriteriaBuilder);
	}

    private static Log logger = LogFactory.getLog(DefaultPartnerRegistrySelectionStrategy.class);


}
