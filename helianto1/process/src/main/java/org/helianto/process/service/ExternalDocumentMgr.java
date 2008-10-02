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

import java.util.List;

import org.helianto.core.Entity;
import org.helianto.process.ExternalDocument;
import org.helianto.process.Operation;
import org.helianto.process.Setup;

/**
 * <code>ProcessMgr</code> interface.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface ExternalDocumentMgr {

    /**
     * Persist an <code>ExternalDocument</code>.
     */
    public void persistExternalDocument(ExternalDocument externalDocument);
    
    /**
     * Persist an <code>Operation</code>.
     */
    public void persistOperation(Operation operation);
    
    /**
     * Persist an <code>Setup</code>.
     */
    public void persistSetup(Setup setup);
    
    /**
     * Find <code>ExternalDocument</code> by <code>Entity</code> and code.
     */
    public ExternalDocument findExternalDocumentByNaturalId(Entity entity, String docCode);
    
    /**
     * Find <code>ExternalDocument</code> list for this <code>Entity</code>
     */
    public List<ExternalDocument> findExternalDocumentByEntity(Entity entity);

    /**
     * Find <code>ExternalDocument</code> list for this <code>Entity</code> where parent is null.
     */
    public List<ExternalDocument> findExternalDocumentRootByEntity(Entity entity);

    /**
     * Find <code>ExternalDocument</code> list for the parent.
     */
    public List<ExternalDocument> findExternalDocumentByParent(ExternalDocument parent);
    
}
