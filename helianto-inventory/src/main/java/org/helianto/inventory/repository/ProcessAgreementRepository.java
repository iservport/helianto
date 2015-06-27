package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.ProcessAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Process agreement repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessAgreementRepository 
	extends JpaRepository<ProcessAgreement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	ProcessAgreement findByEntityAndInternalNumber(Entity entity, long internalNumber);

}