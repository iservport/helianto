package org.helianto.partner.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.enums.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Partner repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PartnerRepository extends JpaRepository<Partner, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param privateEntity
	 * @param partnerType
	 */
	@Query("select partner from Partner partner where privateEntity = ?1 and partnerType = ?2 ")
	Partner findByPrivateEntityAndPartnerType(PrivateEntity privateEntity, PartnerType partnerType);
	
	/**
	 * Find by private entity.
	 * 
	 * @param privateEntity
	 */
	List<Partner> findByPrivateEntity(PrivateEntity privateEntity);
	
}
