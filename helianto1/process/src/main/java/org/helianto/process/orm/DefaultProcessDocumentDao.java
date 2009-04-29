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

import org.helianto.core.filter.CriteriaBuilder;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.springframework.stereotype.Repository;

/**
 * Process document data access.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Repository("processDocumentDao")
public class DefaultProcessDocumentDao extends AbstractProcessDocumentDao {

	@Override
	protected void preProcessFilter(ProcessDocumentFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		if (!filter.getClazz().equals(ProcessDocument.class)) {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Document class is: '"+filter.getClazz()+"'");
	        }
			mainCriteriaBuilder.appendAnd().append(filter.getClazz());
		}
	}

	@Override
	protected void doFilter(ProcessDocumentFilter filter, CriteriaBuilder mainCriteriaBuilder) {
		appendLikeFilter("docName", filter.getDocNameLike(), mainCriteriaBuilder);
		appendEqualFilter("inheritanceType", filter.getInheritanceType(), mainCriteriaBuilder);
		appendEqualFilter("priority", filter.getPriority(), mainCriteriaBuilder);
		appendOrderBy("docCode", mainCriteriaBuilder);
	}

}
