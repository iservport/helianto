package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Feature;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Feature repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface FeatureRepository 
	extends JpaRepository<Feature, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param contextId
	 * @param featureCode
	 * @return
	 */
	@Query("select feature "
			+ "from Feature feature "
			+ "where feature.context.id = ?1 "
			+ "and feature.featureCode = ?2 ")
	public Feature findByContextIdAndFeatureCode(int contextId, String featureCode);
	
	public static final String QUERY = "select new "
			+ "org.helianto.core.repository.FeatureReadAdapter"
			+ "( feature.id"
			+ ", feature.context.id"
			+ ", feature.featureCode"
			+ ", feature.featureName"
			+ ", feature.featureType"
			+ ") "
			+ "from Feature feature ";
	
	/**
	 * List features.
	 * 
	 * @param context
	 * @param featureType
	 * @param page
	 */
	@Query(QUERY
			+ "where feature.context = ?1 "
			+ "and feature.featureType = ?2 ")
	List<FeatureReadAdapter> findByContextAndFeatureType(Operator context, char featureType
			, Pageable page);

	/**
	 * List features.
	 * 
	 * @param contextId
	 * @param featureType
	 * @param page
	 */
	@Query(QUERY
			+ "where feature.context.id = ?1 "
			+ "and feature.featureType = ?2 ")
	Page<Feature> findByContext_IdAndFeatureType(int contextId, char featureType
			, Pageable page);

}
