package org.helianto.core.filter.base;

import org.helianto.core.domain.type.ContextEntity;

/**
 * Base class to filter adapters that require an <code>Operator</code> form.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractContextFilterAdapter<F extends ContextEntity> 
	extends AbstractFilterAdapter<F> {

	private static final long serialVersionUID = 1L;

	/**
	 * Form constructor.
	 * 
	 * @param form
	 */
	public AbstractContextFilterAdapter(F form) {
		super(form);
	}
	
	public boolean isSelection(F form) {
		return form.getContextId()>0;
	}
	
}
