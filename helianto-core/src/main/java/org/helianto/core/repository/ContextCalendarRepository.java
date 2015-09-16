package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.ContextCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Calendar repository.
 * 
 * @author Eldevan Nery Jr
 */
public interface ContextCalendarRepository 
	extends JpaRepository<ContextCalendar, Serializable> 
{
	
	/**
	 * Find by context id and code.
	 * 
	 * @param contextId
	 * @param code
	 */
	List<ContextCalendar> findByContext_idAndCalendarCodeLike(Integer contextId, String code);
	
}
