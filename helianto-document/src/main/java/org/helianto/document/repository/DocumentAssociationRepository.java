package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentAssociationRepository extends JpaRepository<DocumentAssociation, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	DocumentAssociation findByParentAndChild(Document parent, Document child);
	
}
