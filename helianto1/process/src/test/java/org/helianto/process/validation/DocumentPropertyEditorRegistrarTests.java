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

package org.helianto.process.validation;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

import org.helianto.process.ExternalDocument;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.orm.hibernate3.HibernateOperations;

public class DocumentPropertyEditorRegistrarTests extends TestCase {

    DocumentPropertyEditorRegistrar registrar;
    HibernateOperations hibernateTemplate;
    
    public void testExternalDocument() {
        
        ExternalDocument loaded = new ExternalDocument();
        loaded.setDocCode("TEST");
        
        expect(hibernateTemplate.load(ExternalDocument.class, new Long(5))).andReturn(loaded);
        replay(hibernateTemplate);
        
        ExternalDocumentForm externalDocumentForm = new ExternalDocumentForm();
        BeanWrapper bw = new BeanWrapperImpl(externalDocumentForm);
        registrar.registerCustomEditors(bw);
        
        bw.setPropertyValue("externalDocument", "5");
        verify(hibernateTemplate);
        assertSame(loaded, externalDocumentForm.getExternalDocument());
        
        String docCode = ((ExternalDocument) bw.getPropertyValue("externalDocument")).getDocCode();
        assertEquals("TEST", docCode);
        
    }
    
    public void setUp() {
        hibernateTemplate = createMock(HibernateOperations.class);
        registrar = new DocumentPropertyEditorRegistrar();
        registrar.setHibernateTemplate(hibernateTemplate);
    }
    
    public void tearDown() {
        reset(hibernateTemplate);
    }
    
    public class ExternalDocumentForm {
        
        private ExternalDocument externalDocument;

        public ExternalDocument getExternalDocument() { return externalDocument; }

        public void setExternalDocument(ExternalDocument externalDocument) { this.externalDocument = externalDocument; }
        
    }
    
}
