package org.helianto.partner.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.helianto.core.repository.FilterDao;
import org.helianto.partner.PublicEntity;
import org.helianto.partner.PublicEntityFilter;
import org.helianto.partner.PublicEntityKey;
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

	public List<PublicEntity> findPublicEntities(PublicEntityFilter publicEntityFilter) {
		List<PublicEntity> publicEntitiyList = (List<PublicEntity>) publicEntityDao.find(publicEntityFilter);
		if (publicEntitiyList!=null) {
			logger.debug("Found {} public entities.", publicEntitiyList.size());
		}
		return publicEntitiyList;
	}
	
	public PublicEntity storePublicEntity(PublicEntity publicEntity) {
		publicEntityDao.saveOrUpdate(publicEntity);
		return publicEntity;
	}
	
	public void removePublicEntity(PublicEntity publicEntity) {
		publicEntityDao.remove(publicEntity);
	}
	
	public Map<String, PublicEntityKey> loadPublicEntityKeyMap(PublicEntity publicEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PublicEntityKey storePublicEntityKey(PublicEntityKey publicEntityKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void removePublicEntityKey(PublicEntityKey publicEntityKey) {
		// TODO Auto-generated method stub
		
	}
	
	// collabs
	
	private FilterDao<PublicEntity, PublicEntityFilter> publicEntityDao;
	
	@Resource(name="publicEntityDao")
	public void setPublicEntityDao(FilterDao<PublicEntity, PublicEntityFilter> publicEntityDao) {
		this.publicEntityDao = publicEntityDao;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PublicEntityMgrImpl.class);
	
}
