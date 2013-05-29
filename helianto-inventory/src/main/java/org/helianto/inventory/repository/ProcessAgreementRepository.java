package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.inventory.domain.ProcessAgreement;

/**
 * Process agreement repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessAgreementRepository 
	extends FilterRepository<ProcessAgreement, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param internalNumber
	 */
	ProcessAgreement findByEntityAndInternalNumber(Entity entity, long internalNumber);

}