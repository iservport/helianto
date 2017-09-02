package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class KeyTypeRepositoryTests extends AbstractJpaRepositoryIntegrationTest<KeyType, KeyTypeRepository> {

	@Autowired
	private KeyTypeRepository repository;
	
	protected KeyTypeRepository getRepository() {
		return repository;
	}
	
	protected KeyType getNewTarget() {
		return new KeyType("DEFAULT", "CODE");
	}
	
	protected Serializable getTargetId(KeyType target) {
		return target.getId();
	}
	
	protected KeyType findByKey() {
		return getRepository().findByContextNameAndKeyCode("DEFAULT", "CODE");
	}
	
}
