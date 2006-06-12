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

package org.helianto.process.creation;

import org.helianto.core.Entity;
import org.helianto.core.creation.NullEntityException;
import org.helianto.process.Document;

/**
 * A base class to help <code>Document</code> descendants 
 * creation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractDocumentCreator {
    
    protected Document documentFactory(Class clazz, Entity entity, String documentCode) throws NullEntityException {
        return documentFactory(clazz, entity, documentCode, "");
    }
    
    protected Document documentFactory(Class clazz, Entity entity, String documentCode, String documentName) throws NullEntityException {
        if (entity==null) {
            throw new NullEntityException(clazz+" must belong to a valid Entity");
        }
        Document document;
        try {
            document = (Document) clazz.newInstance();
            document.setEntity(entity);
            document.setDocCode(documentCode);
            document.setDocName(documentName);
            return document;
        } catch (Exception e) {
            throw new RuntimeException("Can't instantiate "+clazz);
        }
    }

}
