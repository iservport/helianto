package org.helianto.partner.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.repository.KeyTypeRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.domain.PrivateEntityKey;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PrivateEntityKeyRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PrivateEntityKey, PrivateEntityKeyRepository> {

	@Autowired
	private PrivateEntityKeyRepository repository;
	
	@Autowired
	private PrivateEntityRepository privateEntityRepository;
	
	private PrivateEntity privateEntity;
	
	@Autowired
	private KeyTypeRepository keyTypeRepository;
	
	private KeyType keyType;
	
	protected PrivateEntityKeyRepository getRepository() {
		return repository;
	}
	
	protected PrivateEntityKey getNewTarget() {
		return new PrivateEntityKey(privateEntity, keyType);		
	}
	
	protected Serializable getTargetId(PrivateEntityKey target) {
		return target.getId();
	}
	
	protected PrivateEntityKey findByKey() {
		return getRepository().findByPrivateEntityAndKeyType(privateEntity, keyType);
	}
	
	public void setUp() {
		privateEntity = privateEntityRepository.save(new PrivateEntity(entity));
		keyType = keyTypeRepository.save(new KeyType("DEFAULT", "CODE"));
	}
	
}
