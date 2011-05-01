package org.helianto.inventory.filter.classic;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.filter.classic.AbstractControlFilter;
import org.helianto.inventory.ProcessRequirement;


/**
 * Um filtro para seleção de ordens internas.
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
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
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
