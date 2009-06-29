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
public abstract class AbstractAssociationFormAction<A extends AbstractAssociation<P, C>, P, C> extends AbstractEditAggregateFormAction<A, P> {

	@Override
	protected A doPrepareTarget(RequestContext context, A target) throws Exception {
		return target;
	}

    public abstract String getChildAttributeName();
    
	/**
	 * Retrieve the managed parent.
	 * 
	 * @param managedAssociation
	 * @throws Exception
	 */
    protected P getManagedParent(A managedAssociation) throws Exception {
    	return managedAssociation.getParent();
    }
 
	/**
	 * Retrieve the managed child.
	 * 
	 * @param managedAssociation
	 * @throws Exception
	 */
    protected C getManagedChild(A managedAssociation) throws Exception {
    	return managedAssociation.getChild();
    }
 
	protected void postProcessStoreTarget(RequestContext context, A managedAssociation) throws Exception {
		super.postProcessStoreTarget(context, managedAssociation);
    	C managedChild = getManagedChild(managedAssociation);
    	if (managedChild!=null) {
        	context.getFlowScope().put(getChildAttributeName(), managedChild);
            if (logger.isDebugEnabled()) {
                logger.debug("Managed child updated");
            }
    	}
    }
    
}
