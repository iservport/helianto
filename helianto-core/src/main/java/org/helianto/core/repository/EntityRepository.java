package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Entity repository.
 * 
 * @author mauriciofernandesdecastro
 */
@Transactional
public interface EntityRepository extends JpaRepository<Entity, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param operator
	 * @param alias
	 */
	Entity findByOperatorAndAlias(Operator operator, String alias);
	
	/**
	 * Find adapter.
	 * 
	 * @param entityId
	 */
	@Query("select new "
			+ "org.helianto.core.repository.EntityReadAdapter"
			+ "(entity_.id"
			+ ", entity_.operator.id"
			+ ", 0"
			+ ", entity_.alias"
			+ ", entity_.installDate"
			+ ", entity_.summary"
			+ ", entity_.entityDomain"
			+ ", entity_.externalLogoUrl"
			+ ", entity_.customProperties"
			+ ", entity_.activityState"
			+ ", entity_.entityType"
			+ ", entity_.city.id"
			+ ", entity_.city.cityName"
			+ ", entity_.city.state.id"
			+ ", entity_.city.state.stateCode"
			+ ", entity_.city.state.stateName"
			+ ", entity_.city.state.country.id"
			+ ") "
			+ "from Entity entity_ "
			+ "where entity_.id = ?1 ")
	EntityReadAdapter findAdapter(int entityId);
	
	/**
	 * Find entities by identity id and entity type. 
	 * 
	 * @param identityId
	 * @param entityType
	 * @param acitivityState
	 * @param userState
	 * @param exclusionIds
	 * @param pageable
	 */
	@Query(value="select new "
			+ "org.helianto.core.repository.EntityReadAdapter"
			+ "(parents.parent.entity.id"
			+ ", parents.parent.entity.operator.id"
			+ ", child.id"
			+ ", parents.parent.entity.alias"
			+ ", parents.parent.entity.installDate"
			+ ", parents.parent.entity.summary"
			+ ", parents.parent.entity.entityDomain"
			+ ", parents.parent.entity.externalLogoUrl"
			+ ", parents.parent.entity.customProperties"
			+ ", parents.parent.entity.activityState"
			+ ", parents.parent.entity.entityType"
			+ ", parents.parent.entity.city.id"
			+ ", parents.parent.entity.city.cityName"
			+ ", parents.parent.entity.city.state.id"
			+ ", parents.parent.entity.city.state.stateCode"
			+ ", parents.parent.entity.city.state.stateName"
			+ ", parents.parent.entity.city.state.country.id"
			+ ") "
			+ "from User child "
			+ "join child.parentAssociations parents "
			+ "where lower(parents.parent.userKey) = 'user' "
			+ "and child.identity.id = ?1 "
			+ "and parents.parent.entity.entityType = ?2 "
			+ "and child.entity.activityState = ?3 "
			+ "and child.userState = ?4 "
			+ "and parents.parent.entity.id not in(?5) ")
	Page<EntityReadAdapter> findByIdentityIdAndEntityType(int identityId, char entityType
			, char acitivityState, char userState, List<Integer> exclusionIds, Pageable pageable);
	
	/**
	 * Find by Operator name and alias.
	 * 
	 * @param operator
	 * @param alias
	 */
	@Query("select entity from Entity entity where entity.operator.operatorName = ?1 and entity.alias = ?2 ")
	Entity findByContextNameAndAlias(String contextName, String alias);
	
	/**
	 * Find operator using the entity.
	 * 
	 * @param entityId
	 */
	@Query("select entity.operator "
			+ "from Entity entity "
			+ "where entity.id = ?1 ")
	Operator findOperatorByEntity(int entityId);
	

	
}
