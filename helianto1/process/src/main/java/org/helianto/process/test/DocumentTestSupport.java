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

package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.Entity;
import org.helianto.core.test.AbstractIntegrationTest;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.ExternalDocument;
import org.helianto.process.creation.ExternalDocumentCreator;
import org.helianto.process.dao.ProcessDao;
import org.helianto.process.type.DocumentType;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class DocumentTestSupport extends AbstractIntegrationTest {

    private static int testKey = 1;

    protected String[] getConfigLocations() {
        return new String[] { 
                "deploy/core.xml",
                "deploy/process.xml"
                };
    }

    /*
     * ExternalDocument tests 
     */
    
    /**
     * Test support method to create a <code>ExternalDocument</code>.
     * Optionally takes <code>Entity</code> and <code>DocumentType</code> params.
     */
    public static ExternalDocument createExternalDocument(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        ExternalDocument parent;
        try {
            parent = (ExternalDocument) args[0];
        } catch(ClassCastException e) {
            parent = null;
        } catch(ArrayIndexOutOfBoundsException e) {
            parent = null;
        }
        DocumentType documentType;
        try {
            documentType = (DocumentType) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            documentType = DocumentType.values()[(int)Math.random() * 3];
        }
        ExternalDocument externalDocument = null;
        if (parent == null) {
            externalDocument = ExternalDocumentCreator.externalDocumentFactory(entity, generateKey(20, testKey++), documentType);
        } else {
            externalDocument = ExternalDocumentCreator.externalDocumentFactory(parent, generateKey(20, testKey++), documentType);
        }
        return externalDocument;
    }

    /**
     * Test support method to create and persist a <code>ExternalDocument</code>.
     */
    public static ExternalDocument createAndPersistExternalDocument(ProcessDao processDao) {
        ExternalDocument externalDocument = createExternalDocument();
        if (processDao!=null) {
            processDao.persistDocument(externalDocument);
        }
        return externalDocument;
    }

    /**
     * Test support method to create a <code>ExternalDocument</code> list.
     * @param size of list for each Entity
     * @param entityListSize
     */
    public static List<ExternalDocument> createExternalDocumentList(int size, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createExternalDocumentList(size, entityList);
    }

    public static List<ExternalDocument> createExternalDocumentList(int size, int parentSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);
        return createExternalDocumentList(size, parentSize, entityList);
    }

    public static List<ExternalDocument> createExternalDocumentList(int size, List<Entity> entityList) {
        List<ExternalDocument> externalDocumentList = new ArrayList<ExternalDocument>();
        for (Entity e: entityList) {
            for (int i=0;i<size;i++) {
                externalDocumentList.add(createExternalDocument(e));
            }
        }
        return externalDocumentList;
    }

    public static List<ExternalDocument> createExternalDocumentList(int size, int parentSize, List<Entity> entityList) {
        List<ExternalDocument> externalDocumentList = new ArrayList<ExternalDocument>();
        for (Entity e: entityList) {
            for (int i=0;i<parentSize;i++) {
                ExternalDocument category = createExternalDocument(e, DocumentType.CATEGORY);
                externalDocumentList.add(category);
                for (int j=0;j<size;j++) {
                    ExternalDocument document = createExternalDocument(e, DocumentType.FILE);
                    // FIXME new association domain model
//                    document.setParent(category);
                    externalDocumentList.add(document);
                }
            }
        }
        return externalDocumentList;
    }

    /**
     * Test support method to create and persist a <code>ExternalDocument</code> list.
     * @param hibernateTemplate
     * @param size
     * @param 
     */
    public static List<ExternalDocument> createAndPersistExternalDocumentList(HibernateTemplate hibernateTemplate, int i, int p, int e) {
        List<ExternalDocument> externalDocumentList = createExternalDocumentList(i, p, e);
        for (ExternalDocument x: externalDocumentList) {
            hibernateTemplate.merge(x);
        }
        hibernateTemplate.flush();
        hibernateTemplate.clear();
        return externalDocumentList;
    }
    
    
    
}