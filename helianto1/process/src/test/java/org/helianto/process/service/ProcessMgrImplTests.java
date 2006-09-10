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

package org.helianto.process.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.helianto.core.Entity;
import org.helianto.process.ExternalDocument;
import org.helianto.process.dao.ProcessDao;

public class ProcessMgrImplTests extends TestCase {
    
    private ProcessMgrImpl processMgr;
    
    public void testFindExternalDocumentByNaturalId() {
        Entity entity = new Entity();
        ExternalDocument externalDocument = new ExternalDocument();
        expect(processDao.findExternalDocumentByNaturalId(entity , "CODE"))
            .andReturn(externalDocument);
        replay(processDao);
        
        assertSame(externalDocument, processMgr.findExternalDocumentByNaturalId(entity , "CODE"));
        verify(processDao);
    }
    
    public void testFindExternalDocumentByEntity() {
        Entity entity = new Entity();
        List<ExternalDocument> externalDocumentList = new ArrayList<ExternalDocument>();
        expect(processDao.findExternalDocumentByEntity((entity)))
            .andReturn(externalDocumentList);
        replay(processDao);
        
        assertSame(externalDocumentList, processMgr.findExternalDocumentByEntity(entity));
        verify(processDao);
    }
    
    public void testFindExternalDocumentRootByEntity() {
        Entity entity = new Entity();
        List<ExternalDocument> externalDocumentList = new ArrayList<ExternalDocument>();
        expect(processDao.findExternalDocumentRootByEntity((entity)))
            .andReturn(externalDocumentList);
        replay(processDao);
        
        assertSame(externalDocumentList, processMgr.findExternalDocumentRootByEntity(entity));
        verify(processDao);
    }
    
    public void testFindExternalDocumentByParent() {
        ExternalDocument externalDocument = new ExternalDocument();
        List<ExternalDocument> externalDocumentList = new ArrayList<ExternalDocument>();
        expect(processDao.findExternalDocumentByParent((externalDocument)))
            .andReturn(externalDocumentList);
        replay(processDao);
        
        assertSame(externalDocumentList, processMgr.findExternalDocumentByParent(externalDocument));
        verify(processDao);
    }
    
    //~ setup
    
    @Override
    public void setUp() {
        processDao = createMock(ProcessDao.class);
        processMgr =  new ProcessMgrImpl();
        processMgr.setProcessDao(processDao);
    }
    
    @Override
    public void tearDown() {
        reset(processDao);
    }
    
    //~ collaborators
    
    private ProcessDao processDao;

    public void setProcessMgr(ProcessMgrImpl processMgr) {
        this.processMgr = processMgr;
    }
    
    

}
