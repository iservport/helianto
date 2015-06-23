package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Lead repository.
 * 
 * @author Eldevan Nery Junior
 */
public interface LeadRepository 
	extends JpaRepository<Lead, Serializable>  {
	
	/**
	 * List by principal.
	 * 
	 * @param principal
	 */
	List<Lead> findByPrincipal(String principal);
	
	/**
	 * Find last by principal.
	 * 
	 * @param principal
	 */
	@Query("select lead from Lead lead "
			+ "where lead.id = "
			+ "(select max(l.id) "
			+ " from Lead l "
			+ " where l.principal = ?1 "
			+ " group by l.principal"
			+ ")")
	Lead findLastByPrincipal(String principal);
	
}
