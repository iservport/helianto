package org.helianto.inventory;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.document.AbstractControlFilter;


/**
 * Um filtro para sele��o de ordens internas.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ProcessRequirementFilter extends AbstractControlFilter {

	private static final long serialVersionUID = 1L;
	private Class<? extends ProcessRequirement> clazz;
	
	/**
	 * Empty constructor
	 */
	ProcessRequirementFilter() {
		super();
		setClazz(ProcessRequirement.class);
	}

	/**
	 * Entity constructor.
	 * 
	 * @param entity
	 */
	public ProcessRequirementFilter(Entity entity) {
		this(entity, 0);
	}

	/**
	 * User constructor.
	 * 
	 * @param user
	 */
	public ProcessRequirementFilter(User user) {
		this(user.getEntity(), 0);
		setUser(user);
	}

	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	public ProcessRequirementFilter(Entity entity, long internalNumber) {
		super();
		setEntity(entity);
		setInternalNumber(internalNumber);
		setClazz(null);
		setDateFieldName("requirementDate");
		setToDate(null);
	}
	
	@Override
	protected void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		if (getClazz()!=null) {
			mainCriteriaBuilder.appendAnd().append(getClazz());
		}
		appendOrderBy("requirementDate", mainCriteriaBuilder);
	}
	
	/**
	 * Type filter.
	 */
	public Class<? extends ProcessRequirement> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends ProcessRequirement> clazz) {
		this.clazz = clazz;
	}

}