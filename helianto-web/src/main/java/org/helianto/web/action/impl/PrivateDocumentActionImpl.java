package org.helianto.web.action.impl;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.core.filter.Filter;
import org.helianto.core.security.PublicUserDetails;
import org.helianto.document.DocumentMgr;
import org.helianto.document.domain.PrivateDocument;
import org.helianto.document.filter.PrivateDocumentFilterAdapter;
import org.helianto.web.action.AbstractFilterAction;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * Presentation logic to private documents.
 * 
 * @author mauriciofernandesdecastro
 */
@Component("privateDocumentAction")
public class PrivateDocumentActionImpl extends AbstractFilterAction<PrivateDocument> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected Filter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new PrivateDocumentFilterAdapter(userDetails.getEntity(), "");
	}
	
	@Override
	protected List<PrivateDocument> doFilter(Filter filter) {
		return documentMgr.findPrivateDocuments(filter);
	}
	
	@Override
	protected PrivateDocument doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		return new PrivateDocument(userDetails.getEntity(), "");
	}
	
	@Override
	protected PrivateDocument doStore(PrivateDocument target) {
		return documentMgr.storeDocument(target);
	}
	
	// collabs
	
	private DocumentMgr documentMgr;
	
	@Resource
	public void setDocumentMgr(DocumentMgr documentMgr) {
		this.documentMgr = documentMgr;
	}

}
