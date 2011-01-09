package org.helianto.partner.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
import org.helianto.core.service.PostInstallationMgr;
import org.helianto.partner.PublicAddress;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.PublicEntityKey;
import org.helianto.partner.filter.classic.PublicEntityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation of <code>PublicEntity</code>.
 * 
 * @author mauriciofernandesdecastro
 */
@Service("publicEntityMgr")
public class PublicEntityMgrImpl implements PublicEntityMgr {

	public List<PublicAddress> findPublicAddress(Filter filter) {
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressDao.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
	public PublicAddress storePublicAddress(PublicAddress publicAddress) {
		publicAddressDao.saveOrUpdate(publicAddress);
		return publicAddress;
	}
	
	public void removePublicAddress(PublicAddress publicAddress) {
		publicAddressDao.remove(publicAddress);
	}
	
	public List<? extends PublicEntity> findPublicEntities(PublicEntityFilter publicEntityFilter) {
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityDao.find(publicEntityFilter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	public PublicEntity storePublicEntity(PublicEntity publicEntity) {
		if (publicEntity.preProcessEntityInstallation()) {
			// trigger entity installation
			publicEntity.setEntity(postInstallationMgr.installEntity(publicEntity.getEntity()));
		}
		publicEntityDao.saveOrUpdate(publicEntity);
		return publicEntity;
	}
	
	public void removePublicEntity(PublicEntity publicEntity) {
		publicEntityDao.remove(publicEntity);
	}
	
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity publicEntity) {
		publicEntityDao.saveOrUpdate(publicEntity);
		Map<String, PublicEntityKey> publicEntityKeyMap = new HashMap<String, PublicEntityKey>();
		Set<PublicEntityKey> publicEntityKeys = publicEntity.getPublicEntityKeys();
		for (PublicEntityKey publicEntityKey: publicEntityKeys) {
			publicEntityKeyMap.put(publicEntityKey.getKeyType().getKeyCode(), publicEntityKey);
		}
		return publicEntityKeyMap;
	}
	
	public PublicEntityKey storePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyDao.saveOrUpdate(publicEntityKey);
		return publicEntityKey;
	}
	
	public void removePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyDao.remove(publicEntityKey);
	}
	
	// collabs
	
	private FilterDao<PublicAddress> publicAddressDao;
	private FilterDao<PublicEntity> publicEntityDao;
	private BasicDao<PublicEntityKey> publicEntityKeyDao;
	private PostInstallationMgr postInstallationMgr;
	
	@Resource(name="publicAddressDao")
	public void setPublicAddressDao(FilterDao<PublicAddress> publicAddressDao) {
		this.publicAddressDao = publicAddressDao;
	}
	
	@Resource(name="publicEntityDao")
	public void setPublicEntityDao(FilterDao<PublicEntity> publicEntityDao) {
		this.publicEntityDao = publicEntityDao;
	}
	
	@Resource(name="publicEntityKeyDao")
	public void setPublicEntityKeyDao(BasicDao<PublicEntityKey> publicEntityKeyDao) {
		this.publicEntityKeyDao = publicEntityKeyDao;
	}
	
	@Resource
	public void setPostInstallationMgr(PostInstallationMgr postInstallationMgr) {
		this.postInstallationMgr = postInstallationMgr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PublicEntityMgrImpl.class);
	
}
