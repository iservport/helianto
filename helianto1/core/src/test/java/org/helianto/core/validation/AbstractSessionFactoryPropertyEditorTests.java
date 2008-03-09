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

package org.helianto.core.validation;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;

import java.beans.PropertyEditor;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * 
 * @author Mauricio Fernandes de Castro
 */
public class AbstractSessionFactoryPropertyEditorTests extends TestCase {
    
    protected PropertyEditor propertyEditor;
    
    public void test() {
    	Object loaded = new Object();
        
        expect(sessionFactory.getCurrentSession()).andStubReturn(session);
        replay(sessionFactory);
        
        expect(session.load(Object.class, "123")).andStubReturn(loaded);
        replay(session);
        
        propertyEditor.setAsText("123");
        verify(sessionFactory);
        verify(session);
        
    }
    
    //- collaborators
    
    private SessionFactory sessionFactory;
    private Session session;
    
    @Override
    public void setUp() {
    	sessionFactory = createMock(SessionFactory.class);
        propertyEditor = new SessionFactoryPropertyEditor();
        session = createMock(Session.class);
    }
    
    @Override
    public void tearDown() {
        reset(sessionFactory);
        reset(session);
    }
    
    public class SessionFactoryPropertyEditor extends AbstractSessionPropertyEditor {
        @Override
        public void setAsText(String id) throws IllegalArgumentException {
            setAsText(id, Object.class);
        }
    }
    
}
