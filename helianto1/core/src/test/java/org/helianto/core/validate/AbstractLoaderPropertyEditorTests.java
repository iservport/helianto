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

package org.helianto.core.validate;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import junit.framework.TestCase;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractLoaderPropertyEditorTests extends TestCase {
    
    protected LoaderPropertyEditorStub propertyEditor;
    
    /* Concrete class under test */
    public class LoaderPropertyEditorStub extends
        AbstractLoaderPropertyEditor {

        protected LoaderPropertyEditorStub(PropertyLoader pl) {
            super(pl);
        }

    }
    
    public void testPropertyLoaderDescendants() {
        Object loaded = new Object();
        
        expect(propertyLoader.load(this.getClass(), 123))
            .andReturn(loaded);
        replay(propertyLoader);
        
        propertyEditor.setAsText("123", this.getClass());
        verify(propertyLoader);
        assertSame(propertyEditor.getValue(), loaded);
    }
    
    //- collaborators
    
    private PropertyLoader propertyLoader;
    
    @Override
    public void setUp() {
        propertyLoader = createMock(PropertyLoader.class);
        propertyEditor = new LoaderPropertyEditorStub(propertyLoader);
    }
    
    @Override
    public void tearDown() {
        reset(propertyLoader);
    }

}