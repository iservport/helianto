package org.helianto.partner.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.PrivateSegment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PrivateSegment repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface PrivateSegmentRepository extends JpaRepository<PrivateSegment, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param segmentAlias
	 */
	PrivateSegment findByEntityAndSegmentAlias(Entity entity, String segmentAlias);
	
	/**
	 * Find by entity.
	 * 
	 * @param entity
	 * @param sort
	 */
	List<PrivateSegment> findByEntity(Entity entity, Sort sort);
	
}
