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

package org.helianto.core.internal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * A base class to hold last value for number lists.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractEnumerator implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int version;
    private String typeName;
    private long lastNumber;
    private int startNumber;

    /**
     * Empty constructor.
     */
    public AbstractEnumerator() {
    	setLastNumber(1L);
    	setStartNumber(1);
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Version.
     */
    @Version
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Type name.
     */
    @Column(length=24)
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Last number.
     */
    public long getLastNumber() {
        return this.lastNumber;
    }
    public void setLastNumber(long lastNumber) {
        this.lastNumber = lastNumber;
    }
    
    /**
     * Start number.
     */
    public int getStartNumber() {
		return startNumber;
	}
    public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("typeName").append("='").append(getTypeName()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getTypeName() == null ? 0 : this.getTypeName().hashCode() );
         return result;
   }   

}
