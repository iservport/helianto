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

import org.helianto.core.dao.BasicDao;
import org.helianto.process.ProcessDocument;
import org.helianto.process.test.ProcessDocumentTestSupport;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProcessDocumentDaoIntegrationTests extends AbstractProcessIntegrationTest {
	
	public DefaultProcessDocumentDaoIntegrationTests() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
	
	public void testFindUnique() {
		ProcessDocument processDocument = ProcessDocumentTestSupport.createProcessDocument();
		processDocumentDao.persist(processDocument);
		assertEquals(processDocument, processDocumentDao.findUnique(processDocument.getEntity(), processDocument.getDocCode()));
	}

    //- collabs

    private BasicDao<ProcessDocument> processDocumentDao;
    
    public void setProcessDocumentDao(BasicDao<ProcessDocument> processDocumentDao) {
        this.processDocumentDao = processDocumentDao;
    }
    
}
