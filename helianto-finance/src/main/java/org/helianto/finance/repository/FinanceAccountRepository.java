package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.finance.domain.FinanceAccount;
import org.helianto.partner.domain.Partner;

/**
 * Finance account repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface FinanceAccountRepository 
	extends FilterRepository<FinanceAccount, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param partner
	 * @param compositeAccountCode
	 */
	FinanceAccount findByPartnerAndCompositeAccountCode(Partner partner, String compositeAccountCode);
	
}
