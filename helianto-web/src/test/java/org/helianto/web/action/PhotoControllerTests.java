package org.helianto.web.action;

import static org.junit.Assert.assertEquals;

import org.easymock.classextension.EasyMock;
import org.helianto.core.Identity;
import org.helianto.core.service.UserMgr;
import org.helianto.web.action.impl.PhotoController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class PhotoControllerTests {
	
	private PhotoController photoController;
	
	@Test
	public void laod() {
		Identity identity = new Identity("principal");
		byte[] photo = new byte[] { 65, 66, 67 };
		identity.setPhoto(photo);
		identity.setMultipartFileContentType(MediaType.TEXT_PLAIN.toString());
		
		EasyMock.expect(userMgr.loadIdentity(1)).andReturn(identity);
//		EasyMock.expect(userMgr.loadIdentityPhoto(identity)).andReturn(photo);
		EasyMock.replay(userMgr);
		
		ResponseEntity<byte[]> response = photoController.loadPhotoById(1);
		EasyMock.verify(userMgr);
//		for (byte b: response.getBody()) {
//			System.out.print((char) b);
//		}
		assertEquals(photo, response.getBody());
		
	}
	
	private UserMgr userMgr;
	
	@Before
	public void setUp() {
		photoController = new PhotoController();
		userMgr = EasyMock.createMock(UserMgr.class);
		photoController.setUserMgr(userMgr);
	}
	
	@After
	public void tearDown() {
		EasyMock.reset(userMgr);
	}

}
