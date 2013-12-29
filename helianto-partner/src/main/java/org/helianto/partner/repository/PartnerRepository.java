package org.helianto.partner.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * Partner repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerRepository extends FilterRepository<Partner, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param type
	 */
	@Query("select partner from Partner partner where privateEntity = ?1 and type = ?2")
	Partner findByPrivateEntityAndType(PrivateEntity privateEntity, char type);
	
	/**
	 * Find by private entity.
	 * 
	 * @param privateEntity
	 */
	List<Partner> findByPrivateEntity(PrivateEntity privateEntity);
	
}
