package org.helianto.partner.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.core.data.FilterRepository;
import org.helianto.core.domain.Entity;
import org.helianto.partner.domain.ContactGroup;
import org.springframework.data.jpa.repository.Query;

/**
 * Contact group repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ContactGroupRepository 
	extends FilterRepository<ContactGroup, Serializable> 
{
	
	/**
	 * Find by natural key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	ContactGroup findByEntityAndUserKey(Entity entity, String userKey);
	
	/**
	 * Find by user key.
	 * 
	 * @param entity
	 * @param userKey
	 */
	List<ContactGroup> findByUserKey(String userKey);
	
	/**
	 * Find by parent key.
	 * 
	 * @param parentKey
	 */
	@Query(value="select distinct child from User child " +
    		   	"join child.parentAssociations parents " +
    			"where lower(parents.parent.userKey) like ?1 ")
	List<ContactGroup> findByParent(String parentKey);
}
