package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Account repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface AccountRepository extends JpaRepository<Account, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param accountCode
	 */
	Account findByEntityAndAccountCode(Entity entity, String accountCode);
	
}
