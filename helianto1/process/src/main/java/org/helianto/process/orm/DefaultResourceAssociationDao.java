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


package org.helianto.process.orm;

import org.helianto.core.dao.AbstractBasicDao;
import org.helianto.process.ResourceAssociation;
import org.springframework.stereotype.Repository;

/**
 * Resource association data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("resourceAssociationDao")
public class DefaultResourceAssociationDao extends AbstractBasicDao<ResourceAssociation> {

	@Override
	public Class<? extends ResourceAssociation> getClazz() {
		return ResourceAssociation.class;
	}

	/**
	 * Default keys are parent.id and child.id
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "parent", "child" };
	}

}
