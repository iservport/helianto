package org.helianto.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.helianto.core.Entity;
import org.helianto.core.PublicAddress;
import org.helianto.core.PublicEntity2;
import org.helianto.core.PublicEntityKey;
import org.helianto.core.filter.Filter;
import org.helianto.core.filter.PublicAddressFormFilterAdapter;
import org.helianto.core.filter.PublicEntityFormFilterAdapter;
import org.helianto.core.filter.form.CompositeEntityForm;
import org.helianto.core.filter.form.PublicAddressForm;
import org.helianto.core.filter.form.PublicEntityForm;
import org.helianto.core.repository.BasicDao;
import org.helianto.core.repository.FilterDao;
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

	public List<PublicAddress> findPublicAddress(PublicAddressForm form) {
		Filter filter = new PublicAddressFormFilterAdapter(form);
		List<PublicAddress> publicAddressList = (List<PublicAddress>) publicAddressDao.find(filter);
		if (publicAddressList!=null) {
			logger.debug("Found {} public addresses.", publicAddressList.size());
		}
		return publicAddressList;
	}
	
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
	
	public List<? extends PublicEntity2> findPublicEntities(Filter publicEntityFilter) {
		List<PublicEntity2> publicEntitiyList = (List<PublicEntity2>) publicEntityDao.find(publicEntityFilter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	public List<? extends PublicEntity2> findPublicEntities(PublicEntityForm form) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(form);
		List<PublicEntity2> publicEntitiyList = (List<PublicEntity2>) publicEntityDao.find(filter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	public PublicEntity2 findPublicEntity(Entity entity) {
		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
		List<? extends PublicEntity2> publicEntitiyList = (List<PublicEntity2>) publicEntityDao.find(filter);
		if (publicEntitiyList!=null && publicEntitiyList.size()>0) {
			logger.debug("Found {}.", publicEntitiyList.get(0));
			return publicEntitiyList.get(0);
		}
		return null;
	}
	
	public PublicEntity2 installPublicEntity(Entity entity) {
		PublicEntity2 publicEntity = null;
    	if (entity.getNatureAsArray().length>0) {
    		logger.debug("Looking for existing public entity");
    		PublicEntityFormFilterAdapter filter = new PublicEntityFormFilterAdapter(new CompositeEntityForm(entity));
    		List<? extends PublicEntity2> publicEntities = (List<PublicEntity2>) publicEntityDao.find(filter);
    		if (publicEntities!=null && publicEntities.size()==0) {
    			logger.debug("Installing public entity ...");
    			publicEntity = new PublicEntity2(entity);
    			publicEntityDao.saveOrUpdate(publicEntity);
    			logger.debug("Installed {}.", publicEntity);
    		}
    	}
    	else {
    		logger.warn("Public entity not installed: need to set up entity nature first.");
    	}
		return publicEntity;
	}
	
	public PublicEntity2 storePublicEntity(PublicEntity2 publicEntity) {
		publicEntityDao.saveOrUpdate(publicEntity);
		return publicEntity;
	}
	
	public void removePublicEntity(PublicEntity2 publicEntity) {
		publicEntityDao.remove(publicEntity);
	}
	
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity2 publicEntity) {
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
	private FilterDao<PublicEntity2> publicEntityDao;
	private BasicDao<PublicEntityKey> publicEntityKeyDao;
	
	@Resource(name="publicAddressDao")
	public void setPublicAddressDao(FilterDao<PublicAddress> publicAddressDao) {
		this.publicAddressDao = publicAddressDao;
	}
	
	@Resource(name="publicEntityDao")
	public void setPublicEntityDao(FilterDao<PublicEntity2> publicEntityDao) {
		this.publicEntityDao = publicEntityDao;
	}
	
	@Resource(name="publicEntityKeyDao")
	public void setPublicEntityKeyDao(BasicDao<PublicEntityKey> publicEntityKeyDao) {
		this.publicEntityKeyDao = publicEntityKeyDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PublicEntityMgrImpl.class);
	
}
