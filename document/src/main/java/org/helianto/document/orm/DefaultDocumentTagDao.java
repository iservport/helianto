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


package org.helianto.document.orm;

import org.helianto.core.dao.AbstractBasicDao;
import org.helianto.document.DocumentTag;
import org.springframework.stereotype.Repository;

/**
 * Document tag data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("documentTagDao")
public class DefaultDocumentTagDao extends AbstractBasicDao<DocumentTag> {

	@Override
	public Class<? extends DocumentTag> getClazz() {
		return DocumentTag.class;
	}

	/**
	 * Natural keys are tagCode.id and tagCode
	 */
	@Override
	protected String[] getParams() {
		return new String[] { "document", "tagCode" };
	}

}
