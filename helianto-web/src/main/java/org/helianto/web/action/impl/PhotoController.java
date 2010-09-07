package org.helianto.web.action.impl;

import javax.annotation.Resource;

import org.helianto.core.Identity;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.AbstractRenderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to load a photo from identity.
 * 
 * @author mauriciofernandesdecastro
 */
@Controller
@Transactional
public class PhotoController extends AbstractRenderController {

	/**
	 * Triggered when a photo must be loaded.
	 * 
	 * @param identityId
	 */
	@RequestMapping(value = "/photo/{identityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> loadPhotoById(@PathVariable long identityId) {
		Identity identity = userMgr.loadIdentity(identityId);
		byte[] photo = identity.getPhoto();
		if (photo!=null) {
			logger.debug("Render photo from identity {}.", identity);
			return render(photo, identity.getMultipartFileContentType(), true);
		}
		return null;
	}
	
    // collabs
    
    private UserMgr userMgr;
    
    @Resource
    public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
    
    
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
    
}
