package org.helianto.document.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.document.domain.DocumentSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Document session repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentSessionRepository extends JpaRepository<DocumentSession, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param docCode
	 */
	DocumentSession findByUserIdAndLastEventDate(Integer userId, Date lastEventDate);
	
}
