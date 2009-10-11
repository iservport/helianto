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

package org.helianto.document;

import org.helianto.core.AbstractDateRangeFilter;
import org.helianto.core.filter.CriteriaBuilder;

/**
 * Event filter superclass.
 * 
 * @author Mauricio Fernandes de Castro
 */
public abstract class AbstractEventFilter extends AbstractDateRangeFilter {

	private static final long serialVersionUID = 1L;
	private long internalNumber;
	
	/**
	 * Default constructor.
	 */
	public AbstractEventFilter() {
		super();
	}

	/**
	 * Internal number filter.
	 */
	public long getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(long internalNumber) {
		this.internalNumber = internalNumber;
	}

	@Override
	protected void doSelect(CriteriaBuilder mainCriteriaBuilder) {
		appendEqualFilter("internalNumber", getInternalNumber(), mainCriteriaBuilder);
	}

	public boolean isSelection() {
		return getInternalNumber()>0;
	}

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("user").append("='").append(getUser()).append("' ");			
      buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }

}
