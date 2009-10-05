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

package org.helianto.core.service;

import org.helianto.core.Identity;
import org.springframework.stereotype.Component;

/**
 * Default implementation of <code>PrincipalGenerationStrategy</code>.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("principalGenerationStrategy")
public class DefaultPrincipalGenerationStrategy implements PrincipalGenerationStrategy {

	public void generatePrincipal(Identity identity, int attempt) {
		// first attempt (=0) requires empty principal
		if (!identity.getPrincipal().equals("")) {
			if (attempt==0) {
				return;
			}
		}
		StringBuilder principalCandidate = new StringBuilder();
		if (identity.getPrincipal().equals("")) {
			// try the last name
			if (!identity.getPersonalData().getLastName().equals("")) {
				if (!identity.getPersonalData().getFirstName().equals("")) {
					principalCandidate.append(normalize(
							identity.getPersonalData().getFirstName())
							.substring(0, 1));
				}
				principalCandidate.append(normalize(identity.getPersonalData()
						.getLastName()));
			}
			// try the alias
			else if (!identity.getOptionalAlias().equals("")) {
				principalCandidate
						.append(normalize(identity.getOptionalAlias()));
			}
			// or throw exception
			else {
				throw new IllegalArgumentException(
						"Unable to generate principal: last name and optional alias empty");
			}
			if (attempt > 0) {
				principalCandidate.append(attempt);
			}
			identity.setPrincipal(principalCandidate.toString());
		}

	}

	public String normalize(String token) {
		return token.toLowerCase();
	}

}