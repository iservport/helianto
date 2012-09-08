package org.helianto.resource.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.helianto.core.Entity;
import org.helianto.core.Unit;
import org.helianto.resource.def.ResourceType;
import org.helianto.resource.domain.ResourceGroup;
import org.junit.Test;

public class ResourceDomainTests {
	
	private ResourceGroup resourceGroup;
	private Unit unit;
	
	private Entity entity;
	
	@Test
	public void constructor() {
		unit = new Unit();
		unit.setEntity(new Entity());
		unit.setId(Integer.MAX_VALUE);
		unit.setId(Integer.MIN_VALUE);
		unit.setUnitCode("");
		unit.setUnitName("");
	}
	
	@Test
	public void unitEquals() {
		Entity e = new Entity();
		e.setAlias("E");
		unit = new Unit();
		unit.setEntity(e);
		unit.setUnitCode("123");

        assertTrue(unit.equals(unit));
        assertFalse(unit.equals(null));
        assertFalse(unit.equals(new Object()));
        
        Unit copy = new Unit();
        assertFalse(unit.equals(copy));
        
        copy.setEntity(e);
        copy.setUnitCode("");
        assertFalse(unit.equals(copy));

        copy.setEntity(e);
        copy.setUnitCode("123");
        assertTrue(unit.equals(copy));
    }
	
	@Test
   public void resourceGroup() {
        // ResourceGroup
        resourceGroup = new ResourceGroup();
        entity = new Entity();
        resourceGroup.setEntity(entity);
        resourceGroup.setResourceCode("");
        resourceGroup.setResourceName("");
        resourceGroup.setResourceType(ResourceType.EQUIPMENT.getValue());
        resourceGroup.setResourceType(ResourceType.FIXTURE.getValue());
        resourceGroup.setResourceType(ResourceType.TOOL.getValue());
        
        assertTrue(resourceGroup.equals(resourceGroup));
        assertFalse(resourceGroup.equals(null));
        assertFalse(resourceGroup.equals(new Object()));
        ResourceGroup rg = new ResourceGroup();
        assertFalse(resourceGroup.equals(rg));
        resourceGroup.setResourceCode("123");
        rg.setEntity(null);
        rg.setResourceCode(null);
        assertFalse(resourceGroup.equals(rg));
        rg.setEntity(entity);
        assertFalse(resourceGroup.equals(rg));
        rg.setResourceCode("123");
        assertTrue(resourceGroup.equals(rg));
        

    }

}
