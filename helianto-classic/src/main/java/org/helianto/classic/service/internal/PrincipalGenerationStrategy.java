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

package org.helianto.classic.service.internal;

import org.helianto.core.domain.Identity;

/**
 * Strategy to generate a principal to the <code>Identity</code>.
 * 
 * @author Maurício Fernandes de Castro
 */
public interface PrincipalGenerationStrategy {
	
	/**
	 * Method to generate a principal from <code>Identity</code>
	 * data.
	 * 
	 * <p>If attempt greater than zero, append it as a suffix to the 
	 * principal candidate.
	 * </p>
	 * 
	 * @param identity
	 * @param attempt
	 */
	public void generatePrincipal(Identity identity, int attempt);
	
	/**
	 * Strip forbidden characters from a token.
	 * 
	 * @param token
	 */
	public String normalize(String token);

}
