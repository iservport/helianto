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


package org.helianto.core.filter;

import org.helianto.core.Operator;
import org.helianto.core.Service;
import org.helianto.core.criteria.CriteriaBuilder;
import org.helianto.core.filter.base.AbstractRootFilterAdapter;

/**
 * Service filter adapter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceFilterAdapter extends AbstractRootFilterAdapter<Service> {

	private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     * 
     * @param service
     */
    public ServiceFilterAdapter(Service service) {
    	super(service);
    }

    /**
     * Key constructor.
     * 
     * @param operator
     * @param serviceName
     */
    public ServiceFilterAdapter(Operator operator, String serviceName) {
    	super(new Service(operator, serviceName));
    }

	public boolean isSelection() {
		return getFilter().getServiceName().length()>0;
	}

	public void reset() { }

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("serviceName", getFilter().getServiceName(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) { }

}
