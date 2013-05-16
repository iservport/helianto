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
import org.helianto.core.filter.PublicAddressFormFilterAdapter;
import org.helianto.core.filter.PublicEntityFormFilterAdapter;
import org.helianto.core.form.CompositeEntityForm;
import org.helianto.core.form.PublicAddressForm;
import org.helianto.core.form.PublicEntityForm;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
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
		Filter filter = new PublicAddressFormFilterAdapter(form);
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressDao.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
	@Transactional(readOnly=true)
	public List<PublicAddress> findPublicAddress(Filter filter) {
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressDao.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
	@Transactional
	public PublicAddress storePublicAddress(PublicAddress publicAddress) {
		publicAddressDao.saveOrUpdate(publicAddress);
		return publicAddress;
	}
	
	@Transactional
	public void removePublicAddress(PublicAddress publicAddress) {
		publicAddressDao.remove(publicAddress);
	}
	
	@Transactional(readOnly=true)
	public List<? extends PublicEntity> findPublicEntities(Filter publicEntityFilter) {
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityDao.find(publicEntityFilter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	@Transactional(readOnly=true)
	public List<? extends PublicEntity> findPublicEntities(PublicEntityForm form) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(form);
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityDao.find(filter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	@Transactional(readOnly=true)
	public PublicEntity findPublicEntity(Entity entity) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
		List<? extends PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityDao.find(filter);
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
    		List<? extends PublicEntity> publicEntities = (List<PublicEntity>) publicEntityDao.find(filter);
    		if (publicEntities!=null && publicEntities.size()==0) {
    			logger.debug("Installing public entity ...");
    			publicEntity = publicEntityDao.merge(new PublicEntity(entity));
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
		publicEntityDao.saveOrUpdate(publicEntity);
		return publicEntity;
	}
	
	@Transactional
	public void removePublicEntity(PublicEntity publicEntity) {
		publicEntityDao.remove(publicEntity);
	}
	
	@Transactional(readOnly=true)
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity publicEntity) {
		publicEntityDao.saveOrUpdate(publicEntity);
		Map<String, PublicEntityKey> publicEntityKeyMap = new HashMap<String, PublicEntityKey>();
		Set<PublicEntityKey> publicEntityKeys = publicEntity.getPublicEntityKeys();
		for (PublicEntityKey publicEntityKey: publicEntityKeys) {
			publicEntityKeyMap.put(publicEntityKey.getKeyType().getKeyCode(), publicEntityKey);
		}
		return publicEntityKeyMap;
	}
	
	@Transactional
	public PublicEntityKey storePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyDao.saveOrUpdate(publicEntityKey);
		return publicEntityKey;
	}
	
	@Transactional
	public void removePublicEntityKey(PublicEntityKey publicEntityKey) {
		publicEntityKeyDao.remove(publicEntityKey);
	}
	
	// collabs
	
	private FilterDao<PublicAddress> publicAddressDao;
	private FilterDao<PublicEntity> publicEntityDao;
	private BasicDao<PublicEntityKey> publicEntityKeyDao;
	
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
	
	private static final Logger logger = LoggerFactory.getLogger(PublicEntityMgrImpl.class);
	
}
