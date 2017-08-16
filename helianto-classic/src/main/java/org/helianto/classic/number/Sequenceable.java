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

package org.helianto.classic.number;

import java.io.Serializable;

import org.helianto.core.domain.Entity;


/**
 * Common Interface to entities having internalNumber.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface Sequenceable 
	extends Serializable
{
	
	/**
	 * The owning entity.
	 */
	Entity getEntity();
	
	/**
	 * InternalNumber getter.
	 */
	long getInternalNumber();

	/**
	 * InternalNumber setter.
	 */
	void setInternalNumber(long internalNumber);

	/**
	 * InternalNumber key.
	 */
	String getInternalNumberKey();
	
	/**
	 * The sequence start number.
	 */
	int getStartNumber();

}
