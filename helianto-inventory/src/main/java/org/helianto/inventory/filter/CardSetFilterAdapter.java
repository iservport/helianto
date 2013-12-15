package org.helianto.inventory.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.base.AbstractInternalFilterAdapter;
import org.helianto.inventory.form.CardSetForm;

/**
 * Card set filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class CardSetFilterAdapter 
	extends AbstractInternalFilterAdapter<CardSetForm>
{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * Form constructor.
     * 
     * @param form
     */
    public CardSetFilterAdapter(CardSetForm form) {
    	super(form);
    }

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("processDocument.id", getForm().getProcessDocumentId(), mainCriteriaBuilder);
		appendEqualFilter("processDocument.docCode", getForm().getProcessDocumentCode(), mainCriteriaBuilder);
		appendEqualFilter("cardType", getForm().getCardType(), mainCriteriaBuilder);
	}
	
	@Override
	public String getOrderByString() {
		return "internalNumber";
	}

}
