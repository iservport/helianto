package org.helianto.core.repository;

import java.io.Serializable;

import org.helianto.core.domain.KeyType;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicEntityKey;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PublicEntityKeyRepositoryTests extends AbstractJpaRepositoryIntegrationTest<PublicEntityKey, PublicEntityKeyRepository> {

	@Autowired
	private PublicEntityKeyRepository repository;
	
	@Autowired
	private PublicEntityRepository publicEntity2Repository;
	
	private PublicEntity publicEntity;
	
	@Autowired
	private KeyTypeRepository keyTypeRepository;
	
	private KeyType keyType;
	
	protected PublicEntityKeyRepository getRepository() {
		return repository;
	}
	
	protected PublicEntityKey getNewTarget() {
        publicEntity = publicEntity2Repository.save(new PublicEntity(entity));
		keyType = keyTypeRepository.save(new KeyType("DEFAULT", "CODE"));
		return new PublicEntityKey(publicEntity, keyType);
	}
	
	protected Serializable getTargetId(PublicEntityKey target) {
		return target.getId();
	}
	
	protected PublicEntityKey findByKey() {
		return getRepository().findByPublicEntityAndKeyType(publicEntity, keyType);
	}
	
}
