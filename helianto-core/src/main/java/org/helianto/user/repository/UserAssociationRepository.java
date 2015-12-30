package org.helianto.user.repository;

import java.io.Serializable;
import java.util.List;

import org.helianto.user.domain.UserAssociation;
import org.helianto.user.domain.UserGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * User association repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface UserAssociationRepository 
	extends JpaRepository<UserAssociation, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	UserAssociation findByParentAndChild(UserGroup parent, UserGroup child);
	
	/**
	 * Find by natural key id.
	 * 
	 * @param parentId
	 * @param id
	 */
	UserAssociation findByParentIdAndChildId(int parentId, int id);

	/**
	 * Find by parent.
	 * 
	 * @param parent
	 * @param sort
	 */
	@Query("select userAssociation from UserAssociation userAssociation where userAssociation.parent = ?1 ")
	List<UserAssociation> findByParent(UserGroup parent, Sort sort);
	
	/**
	 * Find by parent user key.
	 * 
	 * @param userKey
	 */
	@Query("select userAssociation from UserAssociation userAssociation where lower(userAssociation.parent.userKey) like ?1 ")
	Iterable<UserAssociation> findByParentUserKey(String userKey);
	
	/**
	 * Find by child user key.
	 * 
	 * @param userKey
	 */
	@Query("select userAssociation from UserAssociation userAssociation where lower(userAssociation.child.userKey) like ?1 ")
	Iterable<UserAssociation> findByChildUserKey(String userKey);
	
}
