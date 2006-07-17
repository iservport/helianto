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

import org.helianto.process.Unit;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.orm.hibernate3.HibernateOperations;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class UnitPropertyEditorRegistrarTests extends TestCase {
    
    UnitPropertyEditorRegistrar registrar;
    HibernateOperations hibernateTemplate;
    
    public void testUnit() {
        
        Unit loaded = new Unit();
        loaded.setUnitCode("TEST");
        
        expect(hibernateTemplate.load(Unit.class, 5)).andReturn(loaded);
        replay(hibernateTemplate);
        
        UnitForm unitForm = new UnitForm();
        BeanWrapper bw = new BeanWrapperImpl(unitForm);
        registrar.registerCustomEditors(bw);
        
        bw.setPropertyValue("unit", "5");
        verify(hibernateTemplate);
        assertSame(loaded, unitForm.getUnit());
        
        String unitCode = ((Unit) bw.getPropertyValue("unit")).getUnitCode();
        assertEquals("TEST", unitCode);
        
    }
    
    public void setUp() {
        hibernateTemplate = createMock(HibernateOperations.class);
        registrar = new UnitPropertyEditorRegistrar();
        registrar.setHibernateTemplate(hibernateTemplate);
    }
    
    public void tearDown() {
        reset(hibernateTemplate);
    }
    
    public class UnitForm {
        
        private Unit unit;

        public Unit getUnit() { return unit; }

        public void setUnit(Unit unit) { this.unit = unit; }
        
    }
    
}
