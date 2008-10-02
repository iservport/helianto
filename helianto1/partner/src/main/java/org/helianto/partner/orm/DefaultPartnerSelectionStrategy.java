package org.helianto.partner.orm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Entity;
import org.helianto.core.filter.AbstractSelectionStrategy;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.core.orm.DefaultCategorySelectionStrategy;
import org.helianto.partner.PartnerFilter;
import org.springframework.stereotype.Repository;

/**
 * Implementation of <code>SelectionStrategy</code>
 * interface using <code>PartnerFilter</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("partnerSelectionStrategy")
public class DefaultPartnerSelectionStrategy extends AbstractSelectionStrategy<PartnerFilter> {

	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
		mainCriteriaBuilder.appendSegment("partnerRegistry.entity.id", "=")
        .append(entity.getId());
    }

	@Override
	protected boolean isSelection(PartnerFilter filter) {
		return false;
	}

	@Override
	protected void doSelect(PartnerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	protected void doFilter(PartnerFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("partnerNameLike", filter.getPartnerNameLike(), mainCriteriaBuilder);
        appendEqualFilter("priority", filter.getPriority(), mainCriteriaBuilder);
        appendEqualFilter("class", filter.getClazz().toString(), mainCriteriaBuilder);
	}

    private static Log logger = LogFactory.getLog(DefaultCategorySelectionStrategy.class);


}
