package org.helianto.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.PublicEntityMgr;
import org.helianto.core.domain.Entity;
import org.helianto.core.domain.PublicAddress;
import org.helianto.core.domain.PublicEntity;
import org.helianto.core.domain.PublicEntityKey;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.PublicAddressFilterAdapter;
import org.helianto.core.filter.PublicEntityFormFilterAdapter;
import org.helianto.core.form.CompositeEntityForm;
import org.helianto.core.form.PublicAddressForm;
import org.helianto.core.form.PublicEntityForm;
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
@Service("publicEntityMgr")
public class PublicEntityMgrImpl implements PublicEntityMgr {

	@Transactional(readOnly=true)
	public List<PublicAddress> findPublicAddress(PublicAddressForm form) {
		Filter filter = new PublicAddressFilterAdapter(form);
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressRepository.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
	@Transactional(readOnly=true)
	public List<PublicAddress> findPublicAddress(Filter filter) {
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressRepository.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
	@Transactional
	public PublicAddress storePublicAddress(PublicAddress publicAddress) {
		return publicAddressRepository.saveAndFlush(publicAddress);
	}
	
	@Transactional
	public void removePublicAddress(PublicAddress publicAddress) {
		publicAddressRepository.delete(publicAddress);
	}
	
	@Transactional(readOnly=true)
	public List<? extends PublicEntity> findPublicEntities(Filter publicEntityFilter) {
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityRepository.find(publicEntityFilter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	@Transactional(readOnly=true)
	public List<? extends PublicEntity> findPublicEntities(PublicEntityForm form) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(form);
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityRepository.find(filter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	@Transactional(readOnly=true)
	public PublicEntity findPublicEntity(Entity entity) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
		List<? extends PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityRepository.find(filter);
		if (publicEntitiyList!=null && publicEntitiyList.size()>0) {
			logger.debug("Found {}.", publicEntitiyList.get(0));
			return publicEntitiyList.get(0);
		}
		return null;
	}
	
	@Transactional
	public PublicEntity installPublicEntity(Entity entity) {
		PublicEntity publicEntity = null;
    	if (entity.getNatureAsArray().length>0) {
    		logger.debug("Looking for existing public entity");
    		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
    		List<? extends PublicEntity> publicEntities = (List<PublicEntity>) publicEntityRepository.find(filter);
    		if (publicEntities!=null && publicEntities.size()==0) {
    			logger.debug("Installing public entity ...");
    			publicEntity = publicEntityRepository.saveAndFlush(new PublicEntity(entity));
    			logger.debug("Installed {}.", publicEntity);
    		}
    	}
    	else {
    		logger.warn("Public entity not installed: need to set up entity nature first.");
    	}
		return publicEntity;
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
