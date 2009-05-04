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


package org.helianto.core;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Service filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServiceFilter extends AbstractUserBackedCriteriaFilter {

	/**
	 * Factory method.
	 * 
	 * @param createUser
	 */
	public static ServiceFilter serviceFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(ServiceFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
    private Operator operator;
    private String serviceName;
    
    /**
     * Default constructor.
     */
    public ServiceFilter() {
    	setServiceName("");
    }

	@Override
	public boolean isSelection() {
		return this.operator!=null && serviceName.length()>0;
	}

	/**
	 * Reset
	 */
	public void reset() {
	}

    /**
     * Operator filter.
     */
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * Service name filter.
     */
    public String getServiceName() {
        return this.serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
