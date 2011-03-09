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


package org.helianto.core.filter.classic;

import org.helianto.core.Entity;
import org.helianto.core.Operator;
import org.helianto.core.User;
import org.helianto.core.criteria.CriteriaBuilder;

/**
 * Service filter.
 * 
 * @author Mauricio Fernandes de Castro
 * @deprecated see ServiceFilterAdapter
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

	public boolean isSelection() {
		return this.operator!=null && serviceName.length()>0;
	}

	public String getObjectAlias() {
		return "service";
	}

	/**
	 * Reset
	 */
	public void reset() {
	}

	/**
	 * Do not raise exception when entity is null. 
	 */
	@Override
	protected boolean requireEntity() {
		return false;
	}

	/**
	 * Restrict entity selection to a given operator, if any. 
	 */
	@Override
	public void preProcessFilter(CriteriaBuilder mainCriteriaBuilder) {
		if (getOperator()!=null) {
			appendEqualFilter("operator.id", getOperator().getId(), mainCriteriaBuilder);
		}
	}

	/**
	 * Overriden because default implementation does not allow other 
	 * entities to be selected.
	 */
	@Override
	protected void appendEntityFilter(Entity entity, CriteriaBuilder mainCriteriaBuilder) {
	}

	@Override
	public void doFilter(CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("serviceName", getServiceName(), mainCriteriaBuilder);
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("serviceName", getServiceName(), mainCriteriaBuilder);
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
