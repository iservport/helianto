package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.Account;

/**
 * Account repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AccountRepository extends FilterRepository<Account, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param accountCode
	 */
	Account findByEntityAndAccountCode(Entity entity, String accountCode);
	
}
