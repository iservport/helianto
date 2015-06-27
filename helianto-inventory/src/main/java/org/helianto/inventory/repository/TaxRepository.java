package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Tax repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TaxRepository 
	extends JpaRepository<Tax, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param processAgreement
	 * @param keyType
	 */
	Tax findByProcessAgreementAndKeyType(ProcessAgreement processAgreement, KeyType keyType);

}