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

import org.helianto.process.Resource;
import org.helianto.process.ResourceGroup;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.orm.hibernate3.HibernateOperations;

public class ResourcePropertyEditorRegistrarTests extends TestCase {
    
    ResourcePropertyEditorRegistrar registrar;
    HibernateOperations hibernateTemplate;
    
    public void testResourceGroup() {
        
        ResourceGroup loaded = new ResourceGroup();
        loaded.setResourceCode("TEST");
        
        expect(hibernateTemplate.load(ResourceGroup.class, 5)).andReturn(loaded);
        replay(hibernateTemplate);
        
        ResourceForm resourceForm = new ResourceForm();
        BeanWrapper bw = new BeanWrapperImpl(resourceForm);
        registrar.registerCustomEditors(bw);
        
        bw.setPropertyValue("resourceGroup", "5");
        verify(hibernateTemplate);
        assertSame(loaded, resourceForm.getResourceGroup());
        
        String textValue = ((ResourceGroup) bw.getPropertyValue("resourceGroup")).getResourceCode();
        assertEquals("TEST", textValue);
        
    }
    
    
    public void setUp() {
        hibernateTemplate = createMock(HibernateOperations.class);
        registrar = new ResourcePropertyEditorRegistrar();
        registrar.setHibernateTemplate(hibernateTemplate);
    }
    
    public void tearDown() {
        reset(hibernateTemplate);
    }
    
    public class ResourceForm {
        
        private ResourceGroup resourceGroup;
        private Resource resource;

        public ResourceGroup getResourceGroup() { return resourceGroup; }

        public void setResourceGroup(ResourceGroup resourceGroup) { this.resourceGroup = resourceGroup; }
        
        public Resource getResource() { return resource; }

        public void setResource(Resource resource) { this.resource = resource; }
        
    }
    
}
