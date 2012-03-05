package org.helianto.web.action;

import java.security.Principal;

import org.helianto.core.Entity;
import org.helianto.core.User;
import org.helianto.core.security.UserDetailsAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

/**
 * Controller to load a photo from identity.
 * 
 * @author mauriciofernandesdecastro
 */
public class AbstractRenderController {

	/**
	 * Render a byte array.
	 * 
	 * @param content
	 * @param multipartFileContentType
	 * @param isNotEmpty
	 */
	protected ResponseEntity<byte[]> render(byte[] content, String multipartFileContentType, boolean isNotEmpty) {
		HttpHeaders responseHeaders = new HttpHeaders();
		if (isNotEmpty) {
			responseHeaders.setContentType(MediaType.parseMediaType(multipartFileContentType));
			responseHeaders.setContentLength(content.length);
			logger.debug("Media type is {}.", multipartFileContentType);
			return new ResponseEntity<byte[]>(content, responseHeaders, HttpStatus.CREATED);
		}
		responseHeaders.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<byte[]>("?".getBytes(),  responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * Convenience to extract the user from the authenticated object.
	 * 
	 * @param authenticatedObject
	 */
	protected User extractUser(Principal authenticatedObject) {
		return ((UserDetailsAdapter) ((Authentication) authenticatedObject).getPrincipal()).getUser();
	}

	/**
	 * Convenience to extract the entity from the authenticated object.
	 * 
	 * @param authenticatedObject
	 */
	protected Entity extractEntity(Principal authenticatedObject) {
		return ((UserDetailsAdapter) ((Authentication) authenticatedObject).getPrincipal()).getEntity();
	}

    private static final Logger logger = LoggerFactory.getLogger(AbstractRenderController.class);
    
}
