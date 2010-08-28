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
	 * @param principal
	 */
	@RequestMapping(value = "/photo/{principal}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> loadPhotoById(@PathVariable String principal) {
		Identity identity = userMgr.findIdentityByPrincipal(principal);
		boolean isNotEmpty = identity!=null && identity.getPhoto().length>0;
		logger.debug("Render {} photo from identity {}.", isNotEmpty ?"":"EMPTY", identity);
		return render(identity.getPhoto(), identity.getMultipartFileContentType(), isNotEmpty);
	}
	
    // collabs
    
    private UserMgr userMgr;
    
    @Resource
    public void setUserMgr(UserMgr userMgr) {
		this.userMgr = userMgr;
	}
    
    
    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);
    
}
