package org.helianto.document.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.form.PrivateDocumentForm;

/**
 * Private document filter adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateDocumentFilterAdapter 
	extends AbstractDocumentFormFilterAdapter<PrivateDocumentForm> {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param form
	 */
	public PrivateDocumentFilterAdapter(PrivateDocumentForm form) {
		super(form);
	}
	
	@Override
	protected boolean hasPolimorphicCriterion() {
		return false;
	}
	
	@Override
	protected boolean hasNavigableCriterion() {
		return false;
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("contentType", getForm().getContentType(), mainCriteriaBuilder);
	}

}
