package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.document.domain.Document;
import org.helianto.document.domain.DocumentAssociation;

/**
 * Document repository interface.
 * 
 * @author mauriciofernandesdecastro
 */
public interface DocumentAssociationRepository extends FilterRepository<DocumentAssociation, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	DocumentAssociation findByParentAndChild(Document parent, Document child);
	
}
