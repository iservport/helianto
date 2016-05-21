package org.helianto.core.classic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.PublicEntityMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicEntityKey;
import org.helianto.core.repository.PublicAddressRepository;
import org.helianto.core.repository.PublicEntityKeyRepository;
import org.helianto.core.repository.PublicEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of <code>PublicEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
//@Service("publicEntityMgr")
public class PublicEntityMgrImpl implements PublicEntityMgr {

	@Transactional
	public PublicAddress storePublicAddress(PublicAddress publicAddress) {
		return publicAddressRepository.saveAndFlush(publicAddress);
	}
	
	@Transactional
	public void removePublicAddress(PublicAddress publicAddress) {
		publicAddressRepository.delete(publicAddress);
	}
	
	@Transactional(readOnly=true)
	public PublicEntity findPublicEntity(Entity entity) {
		return publicEntityRepository.findByEntity(entity);
	}
	
	@Transactional
	public PublicEntity storePublicEntity(PublicEntity publicEntity) {
		return publicEntityRepository.saveAndFlush(publicEntity);
	}
	
	@Transactional
	public void removePublicEntity(PublicEntity publicEntity) {
		publicEntityRepository.delete(publicEntity);
	}
	
	@Transactional(readOnly=true)
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity publicEntity) {
		Map<String, PublicEntityKey> publicEntityKeyMap = new HashMap<String, PublicEntityKey>();
		Set<PublicEntityKey> publicEntityKeys = publicEntity.getPublicEntityKeys();
		for (PublicEntityKey publicEntityKey: publicEntityKeys) {
			publicEntityKeyMap.put(publicEntityKey.getKeyType().getKeyCode(), publicEntityKey);
		}
		return publicEntityKeyMap;
	}
	
	@Transactional
	public PublicEntityKey storePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyRepository.saveAndFlush(publicEntityKey);
		return publicEntityKey;
	}
	
	@Transactional
	public void removePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyRepository.delete(publicEntityKey);
	}
	
	// collabs
	
	private PublicAddressRepository publicAddressRepository;
	private PublicEntityRepository publicEntityRepository;
	private PublicEntityKeyRepository publicEntityKeyRepository;
	
	@Resource
	public void setPublicAddressRepository(PublicAddressRepository publicAddressRepository) {
		this.publicAddressRepository = publicAddressRepository;
	}
	
	@Resource
	public void setPublicEntityRepository(PublicEntityRepository publicEntityRepository) {
		this.publicEntityRepository = publicEntityRepository;
	}
	
	@Resource
	public void setPublicEntityKeyRepository(PublicEntityKeyRepository publicEntityKeyRepository) {
		this.publicEntityKeyRepository = publicEntityKeyRepository;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PublicEntityMgrImpl.class);
	
}
