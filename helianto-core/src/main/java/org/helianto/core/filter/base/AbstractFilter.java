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

package org.helianto.core.filter.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.helianto.core.Prioritizable;
import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class to create criteria using a criteria builder.
 * 
 * @author Mauricio Fernandes de Castro
 */
@SuppressWarnings("serial")
public abstract class AbstractFilter extends AbstractAliasFilter {
	
    private List<String> orderByList =  new ArrayList<String>();
    
    /**
     * Default constructor.
     */
    public AbstractFilter() {
    	super("alias");
    }
    
    /**
     * Alias constructor.
     * 
     * @param alias
     */
    public AbstractFilter(String alias) {
    	super(alias);
    	setOrderByString("");
    }
    
    /**
     * True if add all "order by" string are successful.
     * 
     * @param orderByStringArray
     */
    public boolean addOrderByString(String[] orderByStringArray) {
    	boolean success = true;
    	for (String orderByString: orderByStringArray) {
    		success &= addOrderByString(orderByString);
    	}
		return success;
	}
    
    /**
     * True if add "order by" string is successful.
     * 
     * @param orderByString
     */
    public boolean addOrderByString(String orderByString) {
    	if (!this.orderByList.contains(orderByString)) {
    		if (this.orderByList.add(orderByString)) {
    			if (getOrderByString().length()==0) {
    				setOrderByString(orderByString);
    			}
    		}
    		return true;
    	}
		return false;
	}
    
    /**
     * True if remove "order by" string is successful.
     * 
     * @param orderByString
     */
    public boolean removeOrderByString(String orderByString) {
		return this.orderByList.remove(orderByString);
	}
    
    /**
     * True if order index updated the order by string successfuly.
     * 
     * @param index
     */
    public boolean setOrderBy(int index) {
		if (index >=0 && index < this.orderByList.size()) {
			setOrderByString(this.orderByList.get(index));
			return true;
		}
		return false;
	}
    
	/**
	 * Delegate criteria creation to a builder.
	 */
    @Override
	public String createCriteriaAsString() {
        return createCriteriaAsString(new OrmCriteriaBuilder(getObjectAlias()));
    }
	
	// processors
	
	/**
	 * Hook to the filter post-processor.
	 * 
	 * @param mainCriteriaBuilder
	 */
    @Override
	protected void postProcessFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
    	if (getOrderByString().length()>0) {
    		appendOrderBy(getOrderByString(), (OrmCriteriaBuilder) mainCriteriaBuilder);
    	}
	}
		
	// appenders
	
    /**
     * Base order by segment.
     * 
     * @param fieldContent
     * @param criteriaBuilder
     */
    protected void appendOrderBy(String fieldContent, OrmCriteriaBuilder criteriaBuilder) {
    	String[] fieldNames = fieldContent.split(",");
    	if (fieldNames.length>0) {
    		criteriaBuilder.appendOrderBy(fieldNames);
        }
    }
    
    /**
     * Priority range appender.
     * 
     * @param sample
     * @param mainCriteriaBuilder
     */
	protected void appendPriorityRange(Prioritizable sample, OrmCriteriaBuilder mainCriteriaBuilder) {
		String priorityChain = "0123456789";
		int priority = priorityChain.indexOf(sample.getPriority());
		if (logger.isDebugEnabled()) {
			logger.debug("Prioridade estabelecida: "+priority);
		}
		if (priority>=0) {
			String filterSet = priorityChain.substring(0, priority+1);
			if (logger.isDebugEnabled()) {
				logger.debug("Outras prioridades inclu�das: "+filterSet);
			}
			mainCriteriaBuilder.appendAnd().appendSegment("priority", "in")
			.append(Arrays.toString(filterSet.toCharArray()).replace("[", "(").replace("]", ")"));
		}
	}
	
    private static Logger logger = LoggerFactory.getLogger(AbstractFilter.class);

}
