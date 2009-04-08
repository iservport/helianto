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


package org.helianto.core.orm;

import org.helianto.core.Credential;
import org.helianto.core.dao.AbstractBasicDao;
import org.springframework.stereotype.Repository;

/**
 * Credential data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("credentialDao")
public class DefaultCredentialDao extends AbstractBasicDao<Credential> {

	@Override
	protected String[] getParams() {
		return new String[] { "identity" };
	}

	@Override
	public Class<? extends Credential> getClazz() {
		return Credential.class;
	}

}