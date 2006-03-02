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

package org.helianto.process;

import org.helianto.core.Entity;

import junit.framework.TestCase;

public class DocumentTests extends TestCase {
    
    private Document document;
    
    public void setUp() {
        document = new Document();
    }
    
    public void testId() {
        Long id = Long.MAX_VALUE;
        document.setId(id);
        assertEquals(Long.MAX_VALUE, document.getId().longValue());
    }
    
    public void testEntity() {
        Entity entity = new Entity();
        document.setEntity(entity);
        assertSame(entity, document.getEntity());
    }
    
    public void testDocName() {
        String docName = "Document name";
        document.setDocName(docName);
        assertEquals(docName, document.getDocName());
    }
    
    public void testDocCode() {
        String docCode = "12345678-ABC";
        document.setDocCode(docCode);
        assertEquals(docCode, document.getDocCode());
    }

}
