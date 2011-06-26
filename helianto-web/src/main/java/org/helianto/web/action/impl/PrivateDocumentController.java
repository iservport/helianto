package org.helianto.web.action.impl;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.helianto.document.PrivateDocument;
import org.helianto.document.Uploadable;
import org.helianto.document.filter.PrivateDocumentFilterAdapter;
import org.helianto.document.service.DocumentMgr;
import org.helianto.web.action.AbstractRenderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to load private documents.
 * 
 * @author mauriciofernandesdecastro
 */
@Controller
@RequestMapping(value="/pd")
public class PrivateDocumentController extends AbstractRenderController {

	/**
	 * Triggered when a document must be loaded.
	 * 
	 * @param docCode
	 * @param principal
	 */
	@RequestMapping(value = "/code/{docCode}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> loadContentByCode(@PathVariable String docCode, Principal principal) {
		if (principal==null) {
			logger.warn("Null principal, unable to load private document with code: {}", docCode);
			return null;
		}
		PrivateDocumentFilterAdapter filter = new PrivateDocumentFilterAdapter(extractUser(principal).getEntity(), docCode);
		List<PrivateDocument> privateDocumentList = documentMgr.findPrivateDocuments(filter);
		if (privateDocumentList==null || privateDocumentList.size()==0) {
			logger.warn("Private document not found.");
			return null;
		}
		if (privateDocumentList.size()>1) {
			logger.warn("More than one private document found, loading first.");
		}
		return loadPrivateDocument(privateDocumentList.get(0));
	}
	
	/**
	 * Internal load.
	 * 
	 * @param uploadable
	 */
	protected ResponseEntity<byte[]> loadPrivateDocument(Uploadable uploadable) {
		if (uploadable==null) {
			logger.warn("Trying to load null content");
			return null;
		}
		try {
			byte[] bytes = uploadable.getContent();
			logger.debug("Render content from {}.", uploadable);
			return render(bytes, uploadable.getMultipartFileContentType(), true);
		}
		catch (Exception e) {
			logger.warn("Content may not be uploadable: "+uploadable, e);
		}
		return null;
	}
	
    // collabs
    
    private DocumentMgr documentMgr;
    
    @Resource
    public void setDocumentMgr(DocumentMgr documentMgr) {
		this.documentMgr = documentMgr;
	}
    
    
    
    private static final Logger logger = LoggerFactory.getLogger(PrivateDocumentController.class);
    
}
