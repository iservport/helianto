package org.helianto.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.helianto.controller.AbstractLoadFormAction;
import org.helianto.core.KeyType;
import org.helianto.core.Operator;
import org.helianto.core.service.NamespaceMgr;
import org.springframework.stereotype.Component;

/**
 * Presentation logic to load key types.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("keyTypeLoadAction")
public class KeyTypeLoadFormAction extends AbstractLoadFormAction<KeyType, Operator> {

	@Override
	protected List<KeyType> doLoad(Operator operator) {
		return namespaceMgr.loadKeyTypes(operator);
	}

	@Override
	public String getTargetAttributeName() {
		return "keyType";
	}

	@Override
	public String getParentAttributeName() {
		return "operator";
	}
	
	// collabs
	
	private NamespaceMgr namespaceMgr;
	
	@Resource
	public void setNamespaceMgr(NamespaceMgr namespaceMgr) {
		this.namespaceMgr = namespaceMgr;
	}

//	private static Logger logger = LoggerFactory.getLogger(KeyTypeLoadFormAction.class);

}
