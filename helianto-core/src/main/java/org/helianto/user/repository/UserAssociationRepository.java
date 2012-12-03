package org.helianto.user.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.springframework.data.jpa.repository.Query;

/**
 * User association repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserAssociationRepository 
	extends FilterRepository<UserAssociation, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	UserAssociation findByParentAndChild(UserGroup parent, UserGroup child);
	
	/**
	 * Find by parent user key.
	 * 
	 * @param userKey
	 */
	@Query("select userAssociation a where lower(a.parent.userKey) like ?1 ")
	Iterable<UserAssociation> findByParentUserKey(String userKey);
	
	/**
	 * Find by child user key.
	 * 
	 * @param userKey
	 */
	@Query("select userAssociation a where lower(a.child.userKey) like ?1 ")
	Iterable<UserAssociation> findByChildUserKey(String userKey);
	
}
