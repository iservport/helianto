/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.controller;

import org.helianto.core.AbstractAssociation;
import org.springframework.webflow.execution.RequestContext;

/**
 * Presentation logic base class to handle associations.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractAssociationFormAction<A extends AbstractAssociation<P, C>, P, C> extends AbstractEditTargetFormAction<A> {

	@SuppressWarnings("unchecked")
	@Override
	protected final A doCreateTarget(RequestContext context) throws Exception {
    	P parent = (P) context.getModel().getRequired(getParentAttributeName());
        if (logger.isDebugEnabled()) {
            logger.debug("Parent is "+parent);
        }
        return doCreateTarget(context, parent);
	}
	
	protected abstract A doCreateTarget(RequestContext context, P parent) throws Exception;
	
	@Override
	protected A doPrepareTarget(RequestContext context, A target) throws Exception {
		return target;
	}

	@Override
	protected A doSelectTarget(RequestContext context) throws Exception {
		return null;
	}

	public abstract String getParentAttributeName();
	
}
