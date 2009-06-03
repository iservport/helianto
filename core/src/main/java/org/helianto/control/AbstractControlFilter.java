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

package org.helianto.control;


import org.helianto.core.User;
import org.helianto.core.filter.AbstractDateRangeFilter;
import org.helianto.core.filter.DateRange;

/**
 * Base class to control filters.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractControlFilter extends AbstractDateRangeFilter implements DateRange {

	private static final long serialVersionUID = 1L;
	private int id;
    private long internalNumber;
    private int priority = 0;
    private char resolution = ' ';

	/** 
     * Default constructor.
     */
    public AbstractControlFilter() {
    	super();
    }
    
	/** 
     * Reset. 
     */
    public void reset() {
    	super.reset();
    }
    
	@Override
	public boolean isSelection() {
		return getInternalNumber()>0;
	}

	/** 
     * Primary key. 
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    /**
     * Priority.
     */
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     * Resolution.
     */
    public char getResolution() {
        return this.resolution;
    }
    public void setResolution(char resolution) {
        this.resolution = resolution;
    }
    
	/**
	 * Generic factory method.
	 * 
	 * @param user
	 */
	protected static <T extends AbstractControlFilter> T  internalControlFilterFactory(Class<T> clazz, User user) {
        T taskFilter = null;
		try {
			taskFilter = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create control filter of class "+clazz);
		}
        taskFilter.setUser(user);
        return taskFilter;
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


