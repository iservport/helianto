package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.document.domain.ProcessDocument2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Process document repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentRepository2
	extends JpaRepository<ProcessDocument2, Serializable> {

	ProcessDocument2 findByEntityAndDocCode(Entity entity, String docCode);

}
