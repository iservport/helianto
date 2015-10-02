package org.helianto.core.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.Operator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Key type repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface KeyTypeRepository extends JpaRepository<KeyType, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param context
	 * @param keyCode
	 */
	KeyType findByOperatorAndKeyCode(Operator context, String keyCode);
	
	/**
	 * Find by natural key.
	 * 
	 * @param operatorName
	 * @param keyCode
	 */
	KeyType findByOperator_operatorNameAndKeyCode(String operatorName, String keyCode);
	
	/**
	 * Find by operator.
	 * 
	 * @param operator
	 */
	List<KeyType> findByOperator(Operator operator);
	
	/**
	 * Find adapter.
	 * 
	 * @param keyTypeId
	 */
	@Query("select new "
			+ "org.helianto.core.repository.KeyTypeReadAdapter"
			+ "( keyType_.id"
			+ ", keyType_.operator.id"
		 	+ ", keyType_.keyCode"
			+ ", keyType_.keyGroup"
			+ ", keyType_.keyName"
			+ ", keyType_.purpose"
			+ ", keyType_.synonyms "
			+ ") "
			+ "from KeyType keyType_ "
			+ "where keyType_.id = ?1 ")
	KeyTypeReadAdapter findAdapter(int keyTypeId);
	
	/**
	 * Find by context id and groups.
	 * 
	 * @param contextId
	 * @param keyGroups
	 * @param page
	 */
	@Query("select new "
			+ "org.helianto.core.repository.KeyTypeReadAdapter"
			+ "( keyType_.id"
			+ ", keyType_.operator.id"
		 	+ ", keyType_.keyCode"
			+ ", keyType_.keyGroup"
			+ ", keyType_.keyName"
			+ ", keyType_.purpose"
			+ ", keyType_.synonyms "
			+ ") "
			+ "from KeyType keyType_ "
			+ "where keyType_.operator.id = ?1 "
			+ "and keyType_.keyGroup in ?2 ")
	List<KeyTypeReadAdapter> findByContextId(int contextId, char[] keyGroups, Pageable page);
	
}
