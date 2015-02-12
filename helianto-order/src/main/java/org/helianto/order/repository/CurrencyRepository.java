package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.order.domain.Currency;
import org.helianto.query.data.QueryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author mauriciofernandesdecastro
 *
 */
public interface CurrencyRepository extends
	QueryRepository<Currency, Serializable> {
	
	/**
	* Find by Natural Key
	* 
	* @param context
	* @param internalNumber
	*/
	Currency findByContextIdAndCurrencyCode(int contextId, String currencyCode);

	/**
	 * Find by id.
	 *  
	 * @param currencyId
	 */
	@Query(
			"select new "
			+ "org.helianto.order.repository.CurrencyReadAdapter"
			+ "( currency_.id" 
			+ ", currency_.context.id" 
			+ ", currency_.currencyCode" 
			+ ", currency_.currencyName" 
			+ ") "
			+ "from Currency currency_ "
			+ "where currency_.id = ?1 "
			)
	CurrencyReadAdapter findByCurrencyId(int currencyId);

	/**
	 * Page by context id.
	 * 
	 * @param contextId
	 * @param pageable
	 */
	@Query(
			"select new "
			+ "org.helianto.order.repository.CurrencyReadAdapter"
			+ "( currency_.id" 
			+ ", currency_.context.id" 
			+ ", currency_.currencyCode" 
			+ ", currency_.currencyName" 
			+ ") "
			+ "from Currency currency_ "
			+ "where currency_.context.id = ?1 "
			)
	Page<CurrencyReadAdapter> findByContextId(int contextId, Pageable pageable);

}