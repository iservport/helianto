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

package org.helianto.core.number;

import org.helianto.core.domain.type.RootEntity;

/**
 * Common Interface to entities having publicNumber.
 *  
 * @author Mauricio Fernandes de Castro
 */
public interface Numerable extends RootEntity {
	
	/**
	 * Public number getter.
	 */
	public long getPublicNumber();

	/**
	 * Public number setter.
	 */
	public void setPublicNumber(long internalNumber);

	/**
	 * Public number key.
	 */
	public String getPublicNumberKey();

}
