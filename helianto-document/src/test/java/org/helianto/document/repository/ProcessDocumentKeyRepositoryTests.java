package org.helianto.document.repository;

import java.io.Serializable;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.document.domain.ProcessDocument;
import org.helianto.document.domain.ProcessDocumentKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class ProcessDocumentKeyRepositoryTests 
	extends AbstractJpaRepositoryIntegrationTest<ProcessDocumentKey, ProcessDocumentKeyRepository> {

	@Autowired
	private ProcessDocumentKeyRepository repository;
	
	@Override
	protected ProcessDocumentKeyRepository getRepository() {
		return repository;
	}
	
	private ProcessDocument processDocument;
	private KeyType keyType;
	
	@Autowired
	private ProcessDocumentRepository processDocumentRepository;
	
	@Autowired
	private KeyTypeRepository keyTypeRepository;
	
	@Override
	protected ProcessDocumentKey getNewTarget() {
		return new ProcessDocumentKey(processDocument, keyType);
	}
	
	@Override
	protected Serializable getTargetId(ProcessDocumentKey target) {
		return target.getId();
	}
	
	@Override
	protected ProcessDocumentKey findByKey() {
		return getRepository().findByProcessDocumentAndKeyType(processDocument, keyType);
	}
	
	@Override
	protected void setUp() {
		entity = entityRepository.save(new Entity());
		processDocument = processDocumentRepository.save(new ProcessDocument(entity, "10"));
		keyType = keyTypeRepository.save(new KeyType(operator, "CODE"));
	}

}
