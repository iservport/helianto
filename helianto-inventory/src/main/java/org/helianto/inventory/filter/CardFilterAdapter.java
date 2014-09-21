package org.helianto.inventory.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractFilterAdapter;
import org.helianto.inventory.form.CardForm;

/**
 * Card filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class CardFilterAdapter
	extends AbstractFilterAdapter<CardForm>
{
	
	private static final long serialVersionUID = 1L;

    /**
     * Form constructor.
     * 
     * @param form
     */
	public CardFilterAdapter(CardForm form) {
		super(form);
	}

	public boolean isSelection() {
		return getForm().getCardSetId()>0 && getForm().getCardLabel()!=null && !getForm().getCardLabel().isEmpty();
	}

	@Override
	protected void doSelect(OrmCriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("cardSet.id", getForm().getCardSetId(), mainCriteriaBuilder);
		appendEqualFilter("cardLabel", getForm().getCardLabel(), mainCriteriaBuilder);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		doSelect(mainCriteriaBuilder);
		appendEqualFilter("cardState", getForm().getCardState(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "cardLabel";
	}

}
