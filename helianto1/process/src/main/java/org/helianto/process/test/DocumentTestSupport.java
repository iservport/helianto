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
import org.helianto.core.test.DomainTestSupport;
import org.helianto.core.test.EntityTestSupport;
import org.helianto.process.Document;


/**
 * Class to support <code>DocumentDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentTestSupport {

    private static int testKey;

    /**
     * Test support method to create a <code>Document</code>.
     * @param entity optional Entity 
     * @param docCode optional String 
     */
    public static Document createDocument(Object... args) {
        Entity entity;
        try {
            entity = (Entity) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            entity = EntityTestSupport.createEntity();
        }
        String docCode;
        try {
            docCode = (String) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            docCode = DomainTestSupport.getNonRepeatableStringValue(testKey++, 24);
        }
        Document document = Document.documentFactory(entity, docCode);
        return document;
    }

    /**
     * Test support method to create a <code>Document</code> list.
     *
     * @param documentListSize
     */
    public static List<Document> createDocumentList(int documentListSize) {
        return createDocumentList(documentListSize, 1);
    }

    /**
     * Test support method to create a <code>Document</code> list.
     *
     * @param documentListSize
     * @param entityListSize
     */
    public static List<Document> createDocumentList(int documentListSize, int entityListSize) {
        List<Entity> entityList = EntityTestSupport.createEntityList(entityListSize);

        return createDocumentList(documentListSize, entityList);
    }

    /**
     * Test support method to create a <code>Document</code> list.
     *
     * @param documentListSize
     * @param entityList
     */
    public static List<Document> createDocumentList(int documentListSize, List<Entity> entityList) {
        List<Document> documentList = new ArrayList<Document>();
        for (Entity entity: entityList) {
            for (int i=0;i<documentListSize;i++) {
                documentList.add(createDocument(entity));
            }
        }
        return documentList;
    }

}
