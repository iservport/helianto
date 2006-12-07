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

package org.helianto.process.hibernate;

import java.util.List;

import org.helianto.process.ExternalDocument;
import org.helianto.process.dao.ProcessDao;
import org.helianto.process.test.DocumentTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

public class ProcessDaoImplTests extends DocumentTestSupport  {
    

    private ProcessDao processDao;

    public void testPersistExternalDocument() {
        //write
        ExternalDocument externalDocument = createAndPersistExternalDocument(processDao);
        hibernateTemplate.flush();
        //read
        assertEquals(externalDocument,  processDao.findExternalDocumentByNaturalId(externalDocument.getEntity(), externalDocument.getDocCode()));
    }
    
    private List<ExternalDocument> writeExternalDocumentList() {
        int i = 10;
        int p = 3;
        int e = 2; 
        List<ExternalDocument> externalDocumentList = createAndPersistExternalDocumentList(hibernateTemplate, i, p, e);
        assertEquals((i+1)*p*e, externalDocumentList.size());
        return externalDocumentList;
    }
    
    public void testFindExternalDocument() {
        // write
        List<ExternalDocument> externalDocumentList = writeExternalDocumentList();
        // read
        ExternalDocument externalDocument = externalDocumentList.get((int) Math.random()*externalDocumentList.size());
        assertEquals(externalDocument,  processDao.findExternalDocumentByNaturalId(externalDocument.getEntity(), externalDocument.getDocCode()));
        List<ExternalDocument> list1 = processDao.findExternalDocumentByEntity(externalDocument.getEntity());
        for (ExternalDocument e: list1) {
            assertEquals(externalDocument.getEntity(), e.getEntity());
        }
        ExternalDocument parent = null;
        List<ExternalDocument> list2 = processDao.findExternalDocumentRootByEntity(externalDocument.getEntity());
        for (ExternalDocument e: list2) {
            assertNull(e.getParent());
            parent = e;
        }
        List<ExternalDocument> list3 = processDao.findExternalDocumentByParent(parent);
        for (ExternalDocument e: list3) {
            assertEquals(parent, e.getParent());
        }
    }

    public void testExternalDocumentErrors() {
        try {
             processDao.persistDocument(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
        try {
             processDao.removeDocument(null); fail();
        } catch (IllegalArgumentException e) { 
        } catch (Exception e) { fail(); }
    }

    public void testExternalDocumentDuplicate() {
        // write
        ExternalDocument externalDocument = createAndPersistExternalDocument( processDao);
        hibernateTemplate.clear();
        // duplicate
        try {
            hibernateTemplate.save(externalDocument); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    public void testRemoveExternalDocument() {
        // write
        List<ExternalDocument> externalDocumentList = writeExternalDocumentList();
        // remove
        //FIXME
//        ExternalDocument externalDocument = externalDocumentList.get((int) Math.random()*externalDocumentList.size());
//        processDao.removeDocument(externalDocument);
//        hibernateTemplate.flush();
//        hibernateTemplate.clear();
//        // read
//        List<ExternalDocument> all = (ArrayList<ExternalDocument>) hibernateTemplate.find("from ExternalDocument");
//        assertEquals(externalDocumentList.size()-1, all.size());
//        assertFalse(all.contains(externalDocument));
    }

    public void setProcessDao(ProcessDao processDao) {
        this.processDao = processDao;
    }

}
