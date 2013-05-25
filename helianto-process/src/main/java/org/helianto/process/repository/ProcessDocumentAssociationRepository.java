package org.helianto.process.repository;

import java.io.Serializable;

import org.helianto.core.data.FilterRepository;
import org.helianto.process.domain.ProcessDocument;
import org.helianto.process.domain.ProcessDocumentAssociation;

/**
 * Process document association repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentAssociationRepository extends
		FilterRepository<ProcessDocumentAssociation, Serializable> {
	
	/**
	 * Find by natural key.
	 * 
	 * @param parent
	 * @param child
	 */
	ProcessDocumentAssociation findByParentAndChild(ProcessDocument parent, ProcessDocument child);

}
