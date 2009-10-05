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

/**
 * Extends <tt>TopLevelEntity</tt> to handle a String based
 * unique key.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface TopLevelCodedEntity extends TopLevelEntity {
	
	/**
	 * Set unique key.
	 */
	TopLevelCodedEntity setKey(Entity entity, String internalCode);

}
