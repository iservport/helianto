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
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.process.Material;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Product;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Tree;
import org.helianto.process.Unit;

public class ProcessMgr2Tests extends
    AbstractIntegrationTest {

    private ProcessMgr processMgr;
    
    public void setProcessMgr(ProcessMgr processMgr) {
        this.processMgr = processMgr;
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/dataSource.xml", 
                "deploy/sessionFactory-test.xml",
                "deploy/support.xml",
                "deploy/coreMgr.xml",
                "deploy/processMgr.xml" };
    }
    

    /* (non-Javadoc)
     * @see org.helianto.core.junit.AbstractIntegrationTest#getTestEntity()
     */
    @Override
    protected Entity getTestEntity() {
        Entity entity = super.getTestEntity();
        processMgr.persistEntity(entity);
        return entity;
    }

    public void testOperation() {
        Entity entity = getTestEntity();
        assertNotNull("0", entity.getId());

        // 1 resourceGroupFactory
        Resource resourceGroup = processMgr.resourceGroupFactory(entity);
        assertNotNull("1.1", resourceGroup);
        processMgr.persistResource(resourceGroup);
        
        // 2 process factory
        String processCode = generateKey(24);
        Process process = processMgr.processFactory(entity, processCode);
        assertSame("2.1", entity, process.getEntity());
        assertEquals("2.2", processCode, process.getDocCode());
        
        // 3 operation factory
        // TODO change the code of an operation to be a combination of ....
        String oc1 = generateKey(24);
        Operation op1 = processMgr.operationFactory(process, 1, oc1, 1000);
        List treeList = process.getChildren();
        assertSame("3.1", entity, op1.getEntity());
        assertEquals("3.2", oc1, op1.getDocCode());
        assertEquals("3.3", 1000, op1.getOperationTime());
        assertEquals("3.4", 1, treeList.size());
        assertSame("3.5", op1, ((Tree) treeList.get(0)).getChild());
        String oc2 = generateKey(24);
        String oc3 = generateKey(24);
        Operation op2 = processMgr.operationFactory(process, 2, oc2, 60000);
        Operation op3 = processMgr.operationFactory(process, 3, oc3, 1800000);
        
        // persist
        assertNull("3.6", op1.getId());
        processMgr.persistOperation(op1);
        assertNotNull("3.7", op1.getId());

        assertNull("3.8", op2.getId());
        processMgr.persistOperation(op2);
        assertNotNull("3.9", op2.getId());

        assertNull("3.10", op3.getId());
        processMgr.persistOperation(op3);
        assertNotNull("3.11", op3.getId());

        assertNull("3.12", process.getId());
        processMgr.persistProcess(process);
        assertNotNull("3.13", process.getId());


        
        // 4 setup factory
        Setup su11 = processMgr.setupFactory(op1, resourceGroup, 0, 0, 0);
        assertSame("4.1", op1, su11.getOperation());
        assertSame("4.2", resourceGroup, su11.getResource());
        assertEquals("4.3", 0, su11.getPriority());
        assertEquals("4.4", 0, su11.getSetupTime());
        assertEquals("4.5", 0, su11.getTransportTime());
        Setup su12 = processMgr.setupFactory(op1, resourceGroup, 1, 1000, 0);
        assertEquals("4.6", 1, su12.getPriority());
        assertEquals("4.7", 1000, su12.getSetupTime());
        assertEquals("4.8", 0, su12.getTransportTime());
        Setup su2 = processMgr.setupFactory(op2, resourceGroup, 0, 0, 0);
        assertEquals("4.9", 0, su2.getPriority());
        assertEquals("4.10", 0, su2.getSetupTime());
        assertEquals("4.11", 0, su2.getTransportTime());
        Setup su3 = processMgr.setupFactory(op3, resourceGroup, 0, 0, 1000);
        assertEquals("4.12", 0, su3.getPriority());
        assertEquals("4.13", 0, su3.getSetupTime());
        assertEquals("4.14", 1000, su3.getTransportTime());
        
        assertNull("4.15", su11.getId());
        assertNull("4.16", su12.getId());
        processMgr.persistSetup(su11);
        assertNotNull("4.17", su11.getId());

        assertNotNull("4.18", su12.getId());

        assertNull("4.19", su2.getId());
        processMgr.persistSetup(su2);
        assertNotNull("4.20", su2.getId());

        assertNull("4.21", su3.getId());
        processMgr.persistSetup(su3);
        assertNotNull("4.22", su3.getId());

        // 5 Unit
        
        String unitCode = generateKey(12);
        String unitName = generateKey(15)+" ";
        unitName += unitName; // 32 char
        unitName += unitName; // 64 char
        Unit u1 = processMgr.unitFactory(entity, unitCode, unitName);
        assertSame("5.1", entity, u1.getEntity());
        assertEquals("5.2", unitCode, u1.getUnitCode());
        assertNull("5.3", u1.getParent());
        
        String unitCode2 = generateKey(12);
        Unit u2 = processMgr.unitFactory(u1, unitCode2, unitName);
        assertSame("5.4", entity, u2.getEntity());
        assertSame("5.5", u1, u2.getParent());
        assertEquals("5.6", unitCode2, u2.getUnitCode());
        assertEquals("5.7", unitName, u2.getUnitName());
        
        assertNull("5.8", u1.getId());
        processMgr.persistUnit(u1);
        assertNotNull("5.9", u1.getId());
        
        assertNull("5.10", u2.getId());
        processMgr.persistUnit(u2);
        assertNotNull("5.11", u2.getId());
        
        // 6 Material
        
        String materialName = generateKey(15)+" ";
        materialName += materialName; // 32 char
        materialName += materialName; // 64 char
        materialName += materialName; // 128 char
        Material m1 = processMgr.materialFactory(u1, materialName);
        assertSame("6.2", entity, m1.getEntity());
        assertSame("6.2", u1, m1.getMaterialUnit());
        assertEquals("6.3", materialName, m1.getMaterialName());
        assertNull("6.4", m1.getParent());
        
        Material m2 = processMgr.materialFactory(m1, u2, materialName);
        assertSame("6.5", entity, m2.getEntity());
        assertSame("6.6", m1, m2.getParent());
        
        assertNull("6.7", m1.getId());
        processMgr.persistMaterial(m1);
        assertNotNull("6.8", m1.getId());
        
        assertNull("6.9", m2.getId());
        processMgr.persistMaterial(m2);
        assertNotNull("6.10", m2.getId());
        
        // 7 Part
        
        String partPrefix = generateKey(22);
        Part p1 = processMgr.partFactory(entity, partPrefix+"01", false);
        assertSame("7.1", entity, p1.getEntity());
        assertEquals("7.2", partPrefix+"01", p1.getDocCode());
        assertFalse("7.3", p1.isHasDrawing());
        Part p2 = processMgr.partFactory(entity, partPrefix+"02", false);

        assertNull("7.4", p1.getId());
        processMgr.persistPart(p1);
        assertNotNull("7.5", p1.getId());
        
        assertNull("7.6", p2.getId());
        processMgr.persistPart(p2);
        assertNotNull("7.7", p2.getId());
        
        Part p3 = processMgr.partFactory(p1, partPrefix+"03", false, 1d);
        
        processMgr.associateParts(p3, p2, 20d, 2);

        assertNull("7.8", p3.getId());
        processMgr.persistPart(p3);
        assertNotNull("7.9", p3.getId());
        
        List p3c = p3.getChildren();
        assertEquals("7.10", 1, p3c.size());
        
        processMgr.persistPart(p1);
        List p1c = p1.getChildren();
        assertEquals("7.11", 1, p1c.size());
        
        // 8 Product
        
        Product d1 = processMgr.productFactory(u1, m1, partPrefix+"04", process);
        assertSame("8.1", entity, d1.getEntity());
        assertEquals("8.2", partPrefix+"04", d1.getDocCode());
        assertSame("8.3", u1, d1.getUnit());
        assertSame("8.4", m1, d1.getMaterial());
        List procs = d1.getChildren();
        assertEquals("8.5", 1, procs.size());
        Tree td = (Tree) procs.get(0);
        assertSame("8.6", process, td.getChild());
        
        processMgr.persistProduct(d1);
        
    }

}
