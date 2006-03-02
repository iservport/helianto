package org.helianto.sales.service;


import org.helianto.core.Customer;
import org.helianto.core.Entity;
import org.helianto.core.junit.AbstractIntegrationTest;
import org.helianto.process.Material;
import org.helianto.process.Operation;
import org.helianto.process.Part;
import org.helianto.process.Process;
import org.helianto.process.Product;
import org.helianto.process.Resource;
import org.helianto.process.Setup;
import org.helianto.process.Unit;
import org.helianto.sales.Proposal;
import org.helianto.sales.ProposalState;
import org.helianto.sales.service.SalesMgr;

public class SalesMgrTests extends
    AbstractIntegrationTest {

    private SalesMgr salesMgr;
    
    public void setSalesMgr(SalesMgr salesMgr) {
        this.salesMgr = salesMgr;
    }
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "deploy/dataSource.xml", 
                "deploy/sessionFactory-test.xml",
                "deploy/support.xml",
                "deploy/coreMgr.xml",
                "deploy/salesMgr.xml" };
    }
    

    /* (non-Javadoc)
     * @see org.helianto.core.junit.AbstractIntegrationTest#getTestEntity()
     */
    @Override
    protected Entity getTestEntity() {
        Entity entity = super.getTestEntity();
        salesMgr.persistEntity(entity);
        return entity;
    }

    public void testProposal() {
        Entity entity = getTestEntity();
        assertNotNull("0", entity.getId());

        // 1 prepare
        Resource resourceGroup = salesMgr.resourceGroupFactory(entity);
        assertNotNull("1.1", resourceGroup);
        salesMgr.persistResource(resourceGroup);
        
        String processCode = generateKey(24);
        Process process = salesMgr.processFactory(entity, processCode);

        String prefix = generateKey(22);
        Operation op1 = salesMgr.operationFactory(process, 1, prefix+"01", 1000);
        Operation op2 = salesMgr.operationFactory(process, 2, prefix+"02", 60000);
        Operation op3 = salesMgr.operationFactory(process, 3, prefix+"03", 1800000);
        
        salesMgr.persistOperation(op1);
        salesMgr.persistOperation(op2);
        salesMgr.persistOperation(op3);
        salesMgr.persistProcess(process);

        Setup su11 = salesMgr.setupFactory(op1, resourceGroup, 0, 0, 0);
        Setup su12 = salesMgr.setupFactory(op1, resourceGroup, 1, 1000, 0);
        Setup su2 = salesMgr.setupFactory(op2, resourceGroup, 0, 0, 0);
        Setup su3 = salesMgr.setupFactory(op3, resourceGroup, 0, 0, 1000);
        salesMgr.persistSetup(su11);
        salesMgr.persistSetup(su2);
        salesMgr.persistSetup(su3);

        String unitCode = generateKey(12);
        String unitName = generateKey(15)+" ";
        unitName += unitName; // 32 char
        unitName += unitName; // 64 char
        Unit u1 = salesMgr.unitFactory(entity, unitCode, unitName);
        
        String unitCode2 = generateKey(12);
        Unit u2 = salesMgr.unitFactory(u1, unitCode2, unitName);
        salesMgr.persistUnit(u1);
        salesMgr.persistUnit(u2);

        String materialName = generateKey(15)+" ";
        materialName += materialName; // 32 char
        materialName += materialName; // 64 char
        materialName += materialName; // 128 char
        Material m1 = salesMgr.materialFactory(u1, materialName);
        Material m2 = salesMgr.materialFactory(m1, u2, materialName);
        salesMgr.persistMaterial(m1);
        salesMgr.persistMaterial(m2);

        String partPrefix = generateKey(22);
        Part p1 = salesMgr.partFactory(entity, partPrefix+"01", false);
        Part p2 = salesMgr.partFactory(entity, partPrefix+"02", false);
        salesMgr.persistPart(p1);
        salesMgr.persistPart(p2);

        Part p3 = salesMgr.partFactory(p1, partPrefix+"03", false, 1d);
        salesMgr.associateParts(p3, p2, 20d, 2);
        salesMgr.persistPart(p3);
        salesMgr.persistPart(p1);
        
        Product d1 = salesMgr.productFactory(u1, m1, partPrefix+"04", process);
        salesMgr.persistProduct(d1);
        
        Customer c1 = salesMgr.customerFactory(entity, generateKey(20));
        salesMgr.persistCustomer(c1);
        
        String salesCode = generateKey(24);
        Proposal prop1 = salesMgr.proposalFactory(c1, d1, salesCode);
        assertSame("2.1", c1, prop1.getCustomer());
        assertSame("2.2", d1, prop1.getProduct());
        assertEquals("2.3", salesCode, prop1.getSalesCode());
        assertEquals("2.4", ProposalState.OPEN.getValue(), prop1.getProposalState());
        
        assertNull("2.5", prop1.getId());
        salesMgr.persistProposal(prop1);
        assertNotNull("2.6", prop1.getId());
        
    }

}
