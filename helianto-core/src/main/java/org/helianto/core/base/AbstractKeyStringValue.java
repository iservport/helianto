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

package org.helianto.core.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
/**
 * The content of a key to be associated.
 * 
 * @author Mauricio Fernandes de Castro
 */
@MappedSuperclass
public abstract class AbstractKeyStringValue extends AbstractKeyValue {

    private static final long serialVersionUID = 1L;
    private String keyValue;

    /** 
     * Default constructor
     */
    public AbstractKeyStringValue() {
    	super();
    	setKeyValue("");
    }

    /**
     * Key value.
     */
    @Column(length=20)
    public String getKeyValue() {
        return this.keyValue;
    }
    public AbstractKeyStringValue setKeyValue(String keyValue) {
        this.keyValue = keyValue;
        return this;
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
		if (other instanceof AbstractKeyStringValue) {
			return super.equals(other);
		}
		return false;
	}
   
}