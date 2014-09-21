package org.helianto.inventory.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.core.filter.internal.AbstractEventControlInternalFilterAdapter;
import org.helianto.inventory.form.ProcessRequirementForm;


/**
 * Process requirement filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessRequirementFilterAdapter extends AbstractEventControlInternalFilterAdapter<ProcessRequirementForm> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor.
	 * 
	 * @param form
	 */
	public ProcessRequirementFilterAdapter(ProcessRequirementForm form) {
		super(form);
	}

	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
	}
	
	@Override
	protected String getDateFieldName() {
		return "requirementDate";
	}
	
	@Override
	public String getOrderByString() {
		return "requirementDate";
	}

}
