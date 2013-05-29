package org.helianto.inventory.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.KeyType;
import org.helianto.inventory.domain.ProcessAgreement;
import org.helianto.inventory.domain.Tax;

/**
 * Tax repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface TaxRepository 
	extends FilterRepository<Tax, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param processAgreement
	 * @param keyType
	 */
	Tax findByProcessAgreementAndKeyType(ProcessAgreement processAgreement, KeyType keyType);

}